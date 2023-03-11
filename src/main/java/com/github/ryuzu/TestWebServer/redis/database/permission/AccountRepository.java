package com.github.ryuzu.TestWebServer.redis.database.permission;

import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.Optional;

public interface AccountRepository extends RedisDocumentRepository<Account, String> {
    Optional<Account> findOneById(String name);

    // find by numeric property
//    Iterable<Member> findByRoles(int role);
}
