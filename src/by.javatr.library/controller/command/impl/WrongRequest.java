package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return "Wrong command";
    }
}
