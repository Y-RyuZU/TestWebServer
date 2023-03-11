package com.github.ryuzu.TestWebServer.redis;

import com.github.ryuzu.TestWebServer.redis.database.member.Member;
import com.github.ryuzu.TestWebServer.redis.database.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RedisConnectionDebugController {
    private final MemberRepository repository;

    @GetMapping("/api/redis/export/displayname")
    public Optional<Member> displayname(@RequestParam String key) {
//        return repository.findAll().stream().map(Object::toString).collect(Collectors.joining(","));
        return repository.findOneByDisplayName(key);
    }

    @GetMapping("/api/redis/export/id")
    public Optional<Member> id(@RequestParam String key) {
//        return repository.findAll().stream().map(Object::toString).collect(Collectors.joining(","));
        return repository.findById(key);
    }

    @GetMapping("/api/redis/export/all")
    public Iterable<Member> all() {
//        return repository.findAll().stream().map(Object::toString).collect(Collectors.joining(","));
        return repository.findAll();
    }
}
