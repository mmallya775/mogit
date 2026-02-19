package com.mallya.mogit.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command(name = "init",
        mixinStandardHelpOptions = true,
        description = "Initialize a mogit repository")
public class InitCommand implements Runnable {

    @Option(
            names = {"-b", "--initial-branch"},
            paramLabel = "<branch-name>",
            description = "Set the initial branch name (default: ${DEFAULT-VALUE})",
            defaultValue = "master"
    )
    private String branchName;

    @Override
    public void run() {

        Path mogitFolder = Paths.get(".mogit");

        try {
            Path gitDirectory = Files.createDirectory(mogitFolder);

            Path head = mogitFolder.resolve("HEAD");
            Path refsHeads = mogitFolder.resolve("refs/heads");
            Path objects = mogitFolder.resolve("objects");

            Files.createDirectories(objects);
            Files.createDirectories(refsHeads);
            Files.createFile(refsHeads.resolve(branchName));

            //Reference to the current branch
            Files.writeString(head,
                    "ref: refs/heads/" + branchName + "\n");

            System.out.println("Initialized empty Mogit repository in "
                    + gitDirectory.toAbsolutePath());

        } catch (FileAlreadyExistsException e) {
            System.out.println("Mogit repository already exists ....");
        } catch (IOException e) {
            System.out.println("Initialization failed: " + e.getMessage());
        }

    }
}
