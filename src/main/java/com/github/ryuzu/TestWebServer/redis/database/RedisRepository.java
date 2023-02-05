package com.github.ryuzu.TestWebServer.redis.database;

import com.redis.om.spring.annotations.Query;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.Optional;
import java.util.Set;

public interface RedisRepository extends RedisDocumentRepository<Member, String> {
    Optional<Member> findOneByDisplayName(String name);

    // find by numeric property
    Iterable<Member> findByRoles(int role);
}
