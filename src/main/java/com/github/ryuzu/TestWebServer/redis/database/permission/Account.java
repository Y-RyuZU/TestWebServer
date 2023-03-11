package com.github.ryuzu.TestWebServer.redis.database.permission;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.Value;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Value
@Document
public class Account {
    @Id
    int id;
    @Indexed
    Set<Permission> permissions;
}
