package org.d11.cli;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;

@Component
@Command(name = "d11",
         subcommands = {
             MatchCommand.class,
             MatchDayCommand.class
         })
public class D11Command implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Call D11");
        return 0;
    }
    
}
