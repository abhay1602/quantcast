package com.quantcast;

import com.quantcast.command.CookieCommand;
import picocli.CommandLine;

public class CommandExecutor {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new CookieCommand());
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }

}
