package com.github.ryuzu.TestWebServer.security.oauth2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuthProvider {
    GITHUB("GH", "DiscordBot (https://github.com/ryuzu)"),
    DISCORD("DC", "Github (https://github.com/ryuzu)");

    private final String name;
    private final String agent;
}