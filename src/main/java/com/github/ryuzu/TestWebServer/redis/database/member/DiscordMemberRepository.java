package com.github.ryuzu.TestWebServer.redis.database.member;

import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.Optional;

public interface DiscordMemberRepository extends RedisDocumentRepository<DiscordMember, String> {
    Optional<DiscordMember> findOneByDiscordId(String name);



    // find by numeric property
//    Iterable<Member> findByRoles(int role);
}
