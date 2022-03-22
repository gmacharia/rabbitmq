package com.rabbitmq.demo.config;

import com.rabbitmq.demo.consumer.StudentConsumerListener;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {
    private final PropertiesConfig propertiesConfig;
    private final CachingConnectionFactory cachingConnectionFactory;

    @PostConstruct
    public void admin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory);

        rabbitAdmin.declareQueue(createStudentQueue());
        rabbitAdmin.declareExchange(studentExchange());
        rabbitAdmin.declareBinding(studentBinding());
        rabbitAdmin.initialize();
    }

    /**
     * Create a new student Queue
     */
    public Queue createStudentQueue() {
        return new Queue(propertiesConfig.getStudentQueue());
    }

    /**
     * Create a new student Topic Exchange
     */
    private TopicExchange studentExchange() {
        return new TopicExchange(propertiesConfig.getExchangeName());
    }

    /**
     * Bind the student Queue to the Exchange and Routing Key
     */
    private Binding studentBinding() {
        return BindingBuilder.
                bind(createStudentQueue()).
                to(studentExchange()).
                with(propertiesConfig.getRoutingKey());
    }

    /**
     * Convert request to json
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configs for publishing to a Queue
     */
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    /**
     * Configs for listening to a Queue
     */
    @Bean
    public SimpleMessageListenerContainer listenerContainer(
            StudentConsumerListener studentConsumerListener) {

        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(cachingConnectionFactory);
        listenerContainer.setQueueNames(propertiesConfig.getStudentQueue());
        listenerContainer.setMessageListener(studentConsumerListener);
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        listenerContainer.setConcurrency(propertiesConfig.getRabbitMQListenConcurrency());
        listenerContainer.setPrefetchCount(propertiesConfig.getRabbitMQPrefetch());
        listenerContainer.setDefaultRequeueRejected(false);
        return listenerContainer;
    }
}
