package com.github.ryuzu.TestWebServer.redis.database.member;

import com.redis.om.spring.annotations.Document;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Document
public class DiscordMember extends Member {
    String discordId;
    String discordName;
}
