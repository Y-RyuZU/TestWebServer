package com.github.ryuzu.TestWebServer.utilities.role;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.redis.database.permission.Permission;
import com.github.ryuzu.TestWebServer.redis.database.permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Order;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Configuration
public class RoleUtility {
    private static PriorityQueue<Permission> permissions;

    public RoleUtility(PermissionRepository repository) {
        permissions = new PriorityQueue<>(Comparator.comparing(Permission::getFlag)) {{
            addAll(repository.findAll());
        }};
    }

    public static Role[] getRoles(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).toArray(Role[]::new);
    }

    public static String[] getRolesString(int n) {
        return Arrays.stream(Role.values()).filter(role -> (n >> role.ordinal() & 1) == 1).map(Enum::name).toArray(String[]::new);
    }

    public static String[] getRolesStringWithPrefix(int n) {
        return permissions.stream().filter(role -> (n >> role.getFlag() & 1) == 1).map(role -> "ROLE_" + role.getName()).toArray(String[]::new);
    }

    public static int getFlag(Role... roles) {
        return Arrays.stream(roles).mapToInt(Role::getFlag).sum();
    }
}

