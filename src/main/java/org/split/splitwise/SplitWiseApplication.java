package org.split.splitwise;

import org.split.splitwise.command.CommandRegistry;
import org.split.splitwise.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@EnableJpaAuditing
@SpringBootApplication
public class SplitWiseApplication implements CommandLineRunner {


    private final Scanner sc;
    private final CommandRegistry commandRegistry;

    @Autowired
    public SplitWiseApplication(CommandRegistry commandRegistry) {
        sc=new Scanner(System.in);
        this.commandRegistry=commandRegistry;
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to SplitWise Application");
        while (true){
            System.out.print("Enter command: ");
            String command=sc.nextLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("Exiting...");
                break;
            }
            commandRegistry.executeCommand(command);
        }
    }
}
