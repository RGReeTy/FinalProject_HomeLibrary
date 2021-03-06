package by.javatr.library.controller;

import by.javatr.library.controller.command.Command;
import by.javatr.library.controller.command.CommandProvider;

public final class Controller {

    private final CommandProvider provider = new CommandProvider();

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;

        commandName = request.trim();
        executionCommand = provider.getCommand(commandName);

        String response;
        response = executionCommand.execute(request);

        return response;
    }
}
