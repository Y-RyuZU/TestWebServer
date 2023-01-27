package com.github.ryuzu.TestWebServer.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

public class DiscordBot implements EventListener {
    private static JDA jda;
    private static final String TOKEN = "MY_TOKEN"; // 取得したBotのトークン
    private static final String COMMAND_PREFIX = "!"; // コマンドの接頭辞

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("token")
                .addEventListeners(new DiscordBot()) // commandClientを設定
                .build();
    }

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if(!(event instanceof ReadyEvent readyEvent)) return;
    }
}
