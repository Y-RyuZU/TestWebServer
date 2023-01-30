package com.github.ryuzu.TestWebServer.discord;

import com.github.ryuzu.TestWebServer.role.controller.RoleBindController;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.net.URISyntaxException;

public class DiscordBotCommandListener extends ListenerAdapter {
    public static void register(JDA jda) {
        jda.updateCommands().addCommands(
                Commands.slash("access", "Repeats messages back to you.")
                        .addOption(OptionType.STRING, "message", "The message to repeat.")
                        .addOption(OptionType.INTEGER, "times", "The number of times to repeat the message.")
                ,
                Commands.slash("role", "Register role to accessable path.")
                        .addOption(OptionType.ROLE, "role", "The message to repeat.", true)
                        .addOption(OptionType.STRING, "path", "The number of times to repeat the message.", true)
        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "access" -> {
                String message = event.getOption("message").getAsString();
                int times = event.getOption("times").getAsInt();
                event.reply(message.repeat(times)).queue();
            }
            case "role" -> {
                String role = event.getOption("role").getAsRole().getName();
                String path = event.getOption("path").getAsString();
                try {
                    RoleBindController.bindRole(role, path);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                event.reply("Role " + role + " is binded to " + path).queue();
            }
        }
    }
}
