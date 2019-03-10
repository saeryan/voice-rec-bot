package sae.discordapi.command;

import com.google.common.collect.ImmutableMap;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class CommandListener implements MessageCreateListener {

    private final DiscordApi discordApi;

    private final ImmutableMap<String, Command> commands;
    private static final Command NOOP = new NoopCommand();

    public CommandListener(DiscordApi discordApi) {
        this.discordApi = discordApi;
        commands = ImmutableMap.of(
                "!come", new Join(discordApi)
        );
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Command command = parseCommand(event.getMessage());
        command.execute(event.getMessage());
    }

    private Command parseCommand(Message message) {
        return commands.keySet().stream()
                .filter(message.getContent()::startsWith)
                .findFirst()
                .map(commands::get)
                .orElse(NOOP);
    }
}
