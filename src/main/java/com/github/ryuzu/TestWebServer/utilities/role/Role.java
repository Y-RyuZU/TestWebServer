package com.github.ryuzu.TestWebServer.utilities.role;

import java.util.Arrays;

public enum Role {
    USER, MOD, ADMIN;

    private int flag;

    static {
        Arrays.stream(Role.values()).forEach(role -> role.flag = 1 << role.ordinal());
    }

    public int getFlag() {
        return flag;
    }
}
