package com.github.ryuzu.TestWebServer.security.entity;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.utilities.role.RoleUtility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class AccountDetails implements UserDetails {
    private final Member entity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // ユーザに与えられている権限リストを返却するメソッド
        return AuthorityUtils.createAuthorityList(RoleUtility.getRolesStringWithPrefix(entity.getRoles()));
    }

    @Override
    public String getPassword() { // 登録されているパスワードを返却するメソッド
        return this.entity.getHashedPassword();
    }

    @Override
    public String getUsername() { // ユーザ名を返却するメソッド
        return this.entity.getDisplayName();
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
