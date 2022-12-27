package com.github.ryuzu.TestWebServer.security.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleUtility {

    public static Role[] getRoles(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).map(role -> Role.values()[role.ordinal()]).toArray(Role[]::new);
    }

    public static String[] getRolesString(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).map(role -> Role.values()[role.ordinal()].name()).toArray(String[]::new);
    }

    public static String[] getRolesStringWithPrefix(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).map(role -> "ROLE_" + Role.values()[role.ordinal()].name()).toArray(String[]::new);
    }

    public static int getFlag(Role... roles) {
        return Arrays.stream(roles).mapToInt(Role::getFlag).sum();
    }
}

