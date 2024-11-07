package com.demo.sky.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMQConfiguration {

    public static final String DELAY_EXCHANGE = "order.delay.exchange";
    public static final String DELAY_QUEUE = "order.delay.queue";
    public static final String ROUTING_KEY = "order.delay.routingKey";

    @Bean
    public CustomExchange delayExchange() {
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false,
                Map.of("x-delayed-type", "direct"));
    }

    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(ROUTING_KEY).noargs();
    }
}
