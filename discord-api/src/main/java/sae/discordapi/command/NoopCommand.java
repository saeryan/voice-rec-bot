package sae.discordapi.command;

import org.javacord.api.entity.message.Message;

public class NoopCommand implements Command {

    @Override
    // TODO: 10.03.2019 LMAO
    public Object execute(Message param) {
        return null;
    }
}
