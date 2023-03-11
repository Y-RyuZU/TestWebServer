package com.github.ryuzu.TestWebServer.redis.database.permission;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Value
@Document
public class Permission {
    @Id
    int flag;
    @Indexed
    String name;
    @Indexed
    Set<AcsessablePath> path;

    @Value
    public static class AcsessablePath {
        String path;
        int priority;

        public AcsessablePath(String path) {
            this.path = path;
            this.priority = 1;
        }
    }
}
