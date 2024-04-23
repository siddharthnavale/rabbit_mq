package com.spring_boot.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.jsonQueue.name}")
	private String jsonQueue;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingKey.name}")
	private String routingKey;
	
	@Value("${rabbitmq.jsonRoutingKey.name}")
	private String jsonRoutingKey;
	
	//spring bean for queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	//spring bean for json storing queue
		@Bean
		public Queue jsonQueue() {
			return new Queue(jsonQueue);
		}
	
	
	//spring bean for exchange
	@Bean
	public TopicExchange exchange() {
		 return new TopicExchange(exchange);
	}
	
	//binding between queue and exchange using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue())
				             .to(exchange())
				             .with(routingKey);
	}
		
	//binding between queue and exchange using routing key
		@Bean
		public Binding jsonBinding() {
			return BindingBuilder.bind(jsonQueue())
					             .to(exchange())
					             .with(jsonRoutingKey);
		}
		
		@Bean
		public MessageConverter convertor(){
			return new Jackson2JsonMessageConverter();
		}
		
		@Bean
		public AmqpTemplate getAmqpTemplate(ConnectionFactory connectionFactory) {
			RabbitTemplate rabbitTemplate	=new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(convertor());
			return rabbitTemplate;
			}
	//connection factory, Rabbit Template and Rabbit Admin this bean by default created by spring boot
}
