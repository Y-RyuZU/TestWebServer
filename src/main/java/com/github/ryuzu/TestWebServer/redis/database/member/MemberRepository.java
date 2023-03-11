package com.github.ryuzu.TestWebServer.redis.database.member;

import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends RedisDocumentRepository<Member, String> {
    Optional<Member> findOneByDisplayName(String name);

    boolean existsMemberByDisplayName(String name);
}
