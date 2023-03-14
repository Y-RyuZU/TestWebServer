package com.github.ryuzu.TestWebServer.redis.database.member;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Document
@RequiredArgsConstructor(staticName = "of")
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data(staticConstructor = "of")
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
    @Indexed
    @NonNull
    private MemberType type = MemberType.DEFAULT;

    public Member discord() {
        this.type = MemberType.DISCORD;
        return this;
    }

    public Member github() {
        this.type = MemberType.GITHUB;
        return this;
    }
}
