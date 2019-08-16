package com.gabriel.mc.services;

import com.gabriel.mc.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticate() {
        try {
        return (UserSS) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        } catch (Exception e) {
            return null;
        }
    }
}
