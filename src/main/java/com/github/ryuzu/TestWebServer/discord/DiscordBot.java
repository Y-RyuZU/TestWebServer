package com.github.ryuzu.TestWebServer.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class DiscordBot {
    private static JDA jda;
    private static final String COMMAND_PREFIX = "!"; // コマンドの接頭辞

    public static void initialize() {
        jda = JDABuilder.createDefault(System.getenv("DISCORD_TOKEN"))
                .addEventListeners(new DiscordBotCommandListener())
                .build();
        DiscordBotCommandListener.register(jda);
    }
}
