package org.d11.cli;

import java.util.concurrent.Callable;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

public abstract class ActiveMQCommand implements Callable<Integer> {

    private String queueProperty;
    private JmsTemplate jmsTemplate;
    @Autowired
    private Environment environment;
    private final static Logger logger = LoggerFactory.getLogger(ActiveMQCommand.class);
    
    public ActiveMQCommand(String queueProperty, JmsTemplate jmsTemplate) {
        this.queueProperty = queueProperty;
        this.jmsTemplate = jmsTemplate;
    }
    
    protected abstract String getMessage();
    
    @Override
    public Integer call() throws Exception {
        try {
            String queue = environment.getProperty(this.queueProperty);
            this.jmsTemplate.convertAndSend(queue, getMessage());
            logger.debug("Posted message {} to ActiveMQ queue {}.", getMessage(), queue);
            return 0;
        } catch(JmsException e) {
            ActiveMQConnectionFactory activeMQConnectionFactory = (ActiveMQConnectionFactory)this.jmsTemplate.getConnectionFactory();
            logger.error("Could not connect to ActiveMQ on {}. Is the D11 Camel application running?", activeMQConnectionFactory.getBrokerURL());
            return 1;
        }
    }
    
}
