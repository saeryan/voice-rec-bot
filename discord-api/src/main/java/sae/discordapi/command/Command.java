package sae.discordapi.command;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;

public interface Command<R> {

    R execute(Message param);
}
