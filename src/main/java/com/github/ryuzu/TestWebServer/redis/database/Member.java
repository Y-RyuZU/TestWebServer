package com.github.ryuzu.TestWebServer.redis.database;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.*;
import org.springframework.data.annotation.Id;

@Value
@RequiredArgsConstructor(staticName = "of")
@Document
public class Member {
    @Id
    String id;
    @Searchable
    String DisplayName;
    @Indexed
    String HashedPassword;
    @Indexed
    int Roles;
}
