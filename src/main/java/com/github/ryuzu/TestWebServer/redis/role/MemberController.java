package com.github.ryuzu.TestWebServer.redis.role;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.redis.database.member.MemberRepository;
import com.github.ryuzu.TestWebServer.redis.database.permission.Permission;
import com.github.ryuzu.TestWebServer.redis.database.permission.PermissionRepository;
import com.github.ryuzu.TestWebServer.utilities.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadTestData() {
        return args -> {
            permissionRepository.deleteAll();
            var list = Arrays.stream(Role.values()).map(Role::toString).toList();
            for(var i = 0 ; i < list.size() ; i++) {
                var permission = new Permission(1 << i, list.get(i), new HashSet<>());
                permissionRepository.save(permission);
            }

            memberRepository.deleteAll();
            memberRepository.save(Member.of("Id", "DisplayName", passwordEncoder.encode("HashedPassword"), 1));
            memberRepository.save(Member.of("wafex", "aiueo", passwordEncoder.encode("HashedPassword2"),1 << 2));
            memberRepository.save(Member.of("grewht", "adwasdfw", passwordEncoder.encode("HashedPassword3"),1 << 3));
        };
    }
}
