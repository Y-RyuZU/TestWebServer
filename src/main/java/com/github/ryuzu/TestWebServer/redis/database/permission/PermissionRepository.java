package com.github.ryuzu.TestWebServer.redis.database.permission;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends RedisDocumentRepository<Permission, String> {
    Optional<Permission> findOneByFlag(int flag);
    Optional<Permission> findOneByName(String name);

//    List<Permission> findAllOrderByFlag(int flag);
}
