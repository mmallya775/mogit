package com.mallya.mogit;

import com.mallya.mogit.commands.InitCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "mogit", mixinStandardHelpOptions = true, description = "A git like tool in Java", subcommands = {InitCommand.class})
public class Mogit implements Runnable {

    @Override
    public void run() {
        System.out.println("""
                mogit- a git-like tool with java
                
                Usage:
                    mogit <command> [options]
                
                Commands:
                    init          Initialize a repository
                
                Tip:
                    mogit --help  Show full help
                """);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Mogit()).execute(args);
        System.exit(exitCode);
    }
}
