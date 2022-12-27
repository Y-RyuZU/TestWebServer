package com.github.ryuzu.TestWebServer.security.service;

import java.util.Arrays;

public enum Role {
    ADMIN, MOD, USER;

    private int flag;

    static {
        Arrays.stream(Role.values()).forEach(role -> role.flag = 1 << role.ordinal());
    }

    public int getFlag() {
        return flag;
    }
}
