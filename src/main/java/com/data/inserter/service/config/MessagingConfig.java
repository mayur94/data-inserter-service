package com.data.inserter.service.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

	

	
	public static final String OUTPUT_QUEUE="outputQueue";

	
	@Bean	
	public Queue queue2() {
		return new Queue(OUTPUT_QUEUE);
	}
	
	
	
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	  
	  @Bean public AmqpTemplate template(ConnectionFactory connectionFactory) {
	  final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	  rabbitTemplate.setMessageConverter(converter()); 
	  return rabbitTemplate; 
	  }
	 
}
