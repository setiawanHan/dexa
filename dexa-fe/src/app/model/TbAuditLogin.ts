export interface TbAuditLogin {
  auditId: bigint;
  sessionId: string;
  employeeEmail: string;
  employeeRoleName: string;
  auditExpired: string;
}
