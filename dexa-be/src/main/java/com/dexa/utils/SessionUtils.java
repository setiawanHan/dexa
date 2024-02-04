package com.dexa.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static String generateNewSession(HttpServletRequest servletRequest) {
        // invalidate existing session
        HttpSession currentSession = servletRequest.getSession(false);
        if (currentSession != null)
            currentSession.invalidate();

        // create  new session
        HttpSession newSession = servletRequest.getSession(true);
        return newSession.getId();
    }
}
