package com.dexa.services;

import com.dexa.entities.TbAuditLogin;
import com.dexa.entities.TbEmployee;
import com.dexa.exception.DexaException;
import com.dexa.models.AuthLoginModel;
import com.dexa.models.AuthModel;
import com.dexa.models.AuthUserDetailsModel;
import com.dexa.models.RoleModel;
import com.dexa.repo.AuditRepo;
import com.dexa.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthService {

    private final EmployeeService employeeService;
    private final AuditRepo auditRepo;
    private final PasswordService passwordService;
    @Autowired
    public AuthService(EmployeeService employeeService, AuditRepo auditRepo, PasswordService passwordService) {
        this.employeeService = employeeService;
        this.auditRepo = auditRepo;
        this.passwordService = passwordService;
    }

    @Transactional
    public AuthModel authenticate(AuthLoginModel authLogin, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        // check by employee email, is current session active ? yes, throw exception, no, generate new session
        TbAuditLogin tbAuditLogin = this.getAuditLoginByEmployeeEmail(authLogin.getEmployeeEmail());

        // compare between current datetime and expired datetime
        if (!Objects.isNull(tbAuditLogin)) {
            int comparisonResult = LocalDateTime.now(ZoneId.of("Asia/Jakarta")).compareTo(tbAuditLogin.getAuditExpired());
            if (comparisonResult < 0 || comparisonResult == 0)
                throw new DexaException("session anda sedang aktif dan akan expired pada " + tbAuditLogin.getAuditExpired());
        }

        auditRepo.deleteByEmployeeEmail(authLogin.getEmployeeEmail());

        TbEmployee employee = employeeService.getEmployeeByEmail(authLogin.getEmployeeEmail());
        boolean isPasswordMatch = passwordService.matchPassword(authLogin.getRawPassword(), employee.getEmployeePassword());
        if (!isPasswordMatch)
            throw new DexaException("gagal login. mohon hubungi administrator untuk update password anda.");

        AuthModel userInfo = AuthModel.builder()
                .employeeIsAuthenticated(true)
                .employeeAuthenticationSession(SessionUtils.generateNewSession(servletRequest))
                .userInfo(
                        AuthUserDetailsModel.builder()
                                .employeeId(employee.getEmployeeId())
                                .employeeEmail(employee.getEmployeeEmail())
                                .role(
                                        RoleModel.builder()
                                                .roleId(employee.getRole().getRoleId())
                                                .roleName(employee.getRole().getRoleName())
                                                .build())
                                .build())
                .build();

        TbAuditLogin auditLoginDataPreparation = TbAuditLogin.builder()
                .employeeEmail(userInfo.getUserInfo().getEmployeeEmail())
                .employeeRoleName(userInfo.getUserInfo().getRole().getRoleName())
                .sessionId(userInfo.getEmployeeAuthenticationSession())
                .auditExpired(LocalDateTime.now(ZoneId.of("Asia/Jakarta")).plusHours(3))
                .build();
        auditRepo.save(auditLoginDataPreparation);

        return userInfo;
    }

    @Transactional
    public String logout(String employeeEmail) {
        auditRepo.deleteByEmployeeEmail(employeeEmail);
        return "Logout Done.";
    }

    /* ---------- AUDIT LOGIN ---------- */
    public TbAuditLogin getAuditLoginByEmployeeEmail(String employeeEmail) {
        return auditRepo.findByEmployeeEmail(employeeEmail).orElse(null);
    }

    public boolean employeeSessionIsActive(String employeeEmail) {
        Optional<TbAuditLogin> auditLogin = auditRepo.findByEmployeeEmail(employeeEmail);
        if (Objects.isNull(employeeEmail))
            return false;

        if (auditLogin.isPresent()) {
            int comparisonResult = LocalDateTime.now(ZoneId.of("Asia/Jakarta")).compareTo(auditLogin.get().getAuditExpired());
            return comparisonResult < 0;
        }
        return false;
    }

}
