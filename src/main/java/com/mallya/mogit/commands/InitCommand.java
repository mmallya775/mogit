package com.mallya.mogit.commands;

import picocli.CommandLine.Command;

@Command(name = "init",
        description = "Initialize a mogit repository")
public class InitCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Repository has been initialized!");
    }
}
