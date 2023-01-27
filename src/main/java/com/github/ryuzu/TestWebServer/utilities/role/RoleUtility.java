package com.github.ryuzu.TestWebServer.utilities.role;

import java.util.Arrays;

public class RoleUtility {

    public static Role[] getRoles(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).toArray(Role[]::new);
    }

    public static String[] getRolesString(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).map(Enum::name).toArray(String[]::new);
    }

    public static String[] getRolesStringWithPrefix(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).map(role -> "ROLE_" + role.name()).toArray(String[]::new);
    }

    public static int getFlag(Role... roles) {
        return Arrays.stream(roles).mapToInt(Role::getFlag).sum();
    }
}

