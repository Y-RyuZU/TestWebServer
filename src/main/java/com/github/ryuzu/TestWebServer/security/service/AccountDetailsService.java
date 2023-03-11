package com.github.ryuzu.TestWebServer.security.service;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.redis.database.member.MemberRepository;
import com.github.ryuzu.TestWebServer.security.entity.AccountDetails;
import com.github.ryuzu.TestWebServer.utilities.role.RoleUtility;
import com.github.ryuzu.TestWebServer.utilities.strapi.StrapiWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {          // データベースからアカウント情報を検索するメソッド
        // User一件を取得 userNameが無ければ例外発生
        try {
            // Userを取得
            System.out.println("userName: " + userName);
            var account = repository.findOneByDisplayName(userName).orElseThrow();
            System.out.println("account: " + account);

            return new AccountDetails(account);
        } catch (Exception e) {
            throw new UsernameNotFoundException(userName + " is not found");
        }
    }

    @Transactional
    public void register(String username, String password) {
        repository.save(Member.of(username, username, passwordEncoder.encode(password), 1));
    }
}