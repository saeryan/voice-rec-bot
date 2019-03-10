package sae.discordapi.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class Join implements Command<CompletableFuture<Void>> {

    private final DiscordApi discordApi;

    public Join(DiscordApi discordApi) {
        this.discordApi = discordApi;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    /* Not on webhooks yet, todo NOT BE AUTISTIC. */
    @Override
    public CompletableFuture<Void> execute(Message message) {
        Optional<ServerVoiceChannel> voiceChannel = voiceChannel(message);
        return voiceChannel.map(discordApi.getYourself()::move)
                .get();
    }

    private Optional<ServerVoiceChannel> voiceChannel(Message message) {
        User user = message.getUserAuthor()
                .orElseThrow(() -> new RuntimeException(""));
        return message.getServer()
                .map(user::getConnectedVoiceChannel)
                .orElseThrow(() -> new RuntimeException(""));

    }
}
