package sae.discordapi.command;

import org.javacord.api.DiscordApiBuilder;

public class DiscordBot {

    protected DiscordBot(String token) {
        new DiscordApiBuilder()
                .setToken(token)
                .login()
                .thenApply(api -> {
                    System.out.println(api.createBotInvite());
                    return api.addMessageCreateListener(new CommandListener(api));
                });
    }

    public DiscordBot() {
        this("MzMzOTg4Mzg5MDI2MjY3MTM3.D2afbw.cRHX3tBjKQqQXp38LxwsHgJry2c");
    }

    public static void main(String[] args) {
        DiscordBot discordBot = new DiscordBot();

    }
}
