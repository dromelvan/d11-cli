package org.d11.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import picocli.CommandLine.*;

@Command(name = "match",
         description = "Download and update stats or datetime for a match.")
public class MatchCommand extends ActiveMQCommand {

    @Parameters(index = "0",
                arity = "1",
                description = "Id for the match that will be downloaded.")
    private String matchId;    
    
    @Autowired
    public MatchCommand(JmsTemplate jmsTemplate) {
        super("spring.activemq.queue.match", jmsTemplate);
    }
    
    protected String getMessage() {
        return this.matchId;
    }
    
}
