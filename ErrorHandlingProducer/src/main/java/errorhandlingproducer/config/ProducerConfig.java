package errorhandlingproducer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {
	
	@Bean
	Queue deadLetterQueue() {
		return new Queue("nisum.dlq");
	}
	@Bean
	DirectExchange dlqExchange() {
		return new DirectExchange("nisum-dlq-exchange");
	}
	
	@Bean
	DirectExchange nisumExchange() {
		return new DirectExchange("nisumExchange");
	}
	@Bean
	Queue nisumQueue() {
		return  QueueBuilder.durable("nisum.updates").withArgument("x-dead-letter-exchange", "nisum-dlq-exchange").
				withArgument("x-dead-letter-routingKey", "deadLetterRoutingKey").build();
	}
	
	@Bean
	Binding nisumQueueBinding(Queue nisumQueue,DirectExchange nisumExchange) {
		return BindingBuilder.bind(nisumQueue).to(nisumExchange).with("nisumRoutingKey");
	}
	@Bean
	Binding dlqBinding(Queue deadLetterQueue,DirectExchange dlqExchange) {
		return BindingBuilder.bind(deadLetterQueue).to(dlqExchange).with("deadLetterRoutingKey");
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
