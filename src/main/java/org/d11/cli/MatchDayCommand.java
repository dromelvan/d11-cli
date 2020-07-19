package org.d11.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import picocli.CommandLine.*;

@Command(name = "matchDay",
         description = "Download and update stats or datetime for a match day.")
public class MatchDayCommand extends ActiveMQCommand {

    @Parameters(index = "0",
                arity = "1",
                description = "Id for the match day that will be downloaded.")
    private String matchDayId;
    
    @Autowired
    public MatchDayCommand(JmsTemplate jmsTemplate) {
        super("spring.activemq.queue.matchDay", jmsTemplate);
    }
    
    protected String getMessage() {
        return this.matchDayId;
    }

}
