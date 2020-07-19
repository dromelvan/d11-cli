package org.d11.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class D11CliApplication implements CommandLineRunner, ExitCodeGenerator {

    private IFactory factory;
    private D11Command d11Command;    
    private int exitCode;
    
    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(D11CliApplication.class, args)));
    }
    
    @Autowired
    public D11CliApplication(IFactory factory, D11Command d11Command) {
        this.factory = factory;
        this.d11Command = d11Command;
    }
    
    @Override
    public int getExitCode() {
        return this.exitCode;
    }

    @Override
    public void run(String... args) throws Exception {
        this.exitCode = new CommandLine(d11Command, factory).execute(args);        
    }

}
