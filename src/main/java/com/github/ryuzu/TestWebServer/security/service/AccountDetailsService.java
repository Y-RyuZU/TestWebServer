package com.github.ryuzu.TestWebServer.security.service;

import com.github.ryuzu.TestWebServer.utilities.role.RoleUtility;
import com.github.ryuzu.TestWebServer.utilities.strapi.StrapiWrapper;
import com.github.ryuzu.TestWebServer.security.entity.AccountEntity;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class AccountDetailsService implements UserDetailsService {
    private final StrapiWrapper<AccountEntity> wrapper;

    public AccountDetailsService(RestTemplateBuilder builder) {
        wrapper = new StrapiWrapper<>("members", builder, "STRAPI_TOKEN") {};
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {          // データベースからアカウント情報を検索するメソッド

        if (userName == null || "".equals(userName))
            throw new UsernameNotFoundException(userName + "is not found");

        // User一件を取得 userNameが無ければ例外発生
        try {
            // Userを取得
            AccountEntity account = wrapper.findUnique(new HashMap<>(){{put("[DisplayName][$eq]", userName);}});

            Collection<GrantedAuthority> authority =
                    Arrays.stream(RoleUtility.getRolesStringWithPrefix(account.getRoles()))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            return new User(account.getDisplayName(), account.getHashedPassword(), authority);
        } catch (Exception e) {
            throw new UsernameNotFoundException(userName + "is not found");
        }
    }
}