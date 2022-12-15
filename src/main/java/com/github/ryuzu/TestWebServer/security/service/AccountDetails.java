package com.github.ryuzu.TestWebServer.security.service;

import com.github.ryuzu.TestWebServer.security.entity.AccountEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AccountDetails implements UserDetails {
    private final AccountEntity entity;

    public AccountDetails(AccountEntity entity) {
        this.entity = entity;
    }

    public AccountEntity getUser() {
        return entity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // ユーザに与えられている権限リストを返却するメソッド
        return AuthorityUtils.createAuthorityList("ROLE_" /*+ this.entity.getRole().toString()*/);
    }

    @Override
    public String getPassword() { // 登録されているパスワードを返却するメソッド
        return this.entity.getPassword();
    }

    @Override
    public String getUsername() { // ユーザ名を返却するメソッド
        return this.entity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { // アカウントの有効期限の状態を判定するメソッド
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // アカウントのロック状態を判定するメソッド
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 資格情報の有効期限の状態を判定するメソッド
        return true;
    }

    @Override
    public boolean isEnabled() { // 有効なユーザかを判定するメソッド
        return true;
    }
}
