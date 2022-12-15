package com.github.ryuzu.TestWebServer.security.service;

import com.github.ryuzu.TestWebServer.security.entity.AccountEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class AccountDetailsService implements UserDetailsService {
//    private final AccountEntity entity;
//
//    public AccountDetailsService(AccountEntity entity) {
//        this.entity = entity;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userName)
//            throws UsernameNotFoundException {          // データベースからアカウント情報を検索するメソッド
//
//        if (userName == null || "".equals(userName)) {
//            throw new UsernameNotFoundException(userName + "is not found");
//        }
//
//        // User一件を取得 userNameが無ければ例外発生
//        try {
//            // Userを取得
//            AccountEntity account = entity.findUserByUserName(userName);
//
//            if (myUser != null) {
//                return new AccountDetails(myUser); // UserDetailsの実装クラスを生成
//
//            } else {
//                throw new UsernameNotFoundException(userName + "is not found");
//            }
//
//        } catch (EmptyResultDataAccessException e) {
//            throw new UsernameNotFoundException(userName + "is not found");
//        }
//    }
//}