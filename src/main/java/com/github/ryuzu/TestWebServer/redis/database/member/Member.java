package com.github.ryuzu.TestWebServer.redis.database.member;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Document
@RequiredArgsConstructor(staticName = "of")
@SuperBuilder
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Member {
    @Id
    @Indexed
    @NonNull
    private String uuid;
    @Searchable
    @NonNull
    private String displayName;
    @Indexed
    @NonNull
    private String hashedPassword;
    @Indexed
    @NonNull
    private Integer roles;
}
