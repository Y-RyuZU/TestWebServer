package com.github.ryuzu.TestWebServer.security.entity;

import lombok.Value;

@Value
public class AccountEntity {
    public enum Role {
        ADMIN, USER
    }

    String username;
    String password;
//    Role role;
}
