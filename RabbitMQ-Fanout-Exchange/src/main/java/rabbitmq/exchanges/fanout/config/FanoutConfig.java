package rabbitmq.exchanges.fanout.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

	@Bean
	Queue nisumHyd() {
		return new Queue("nisum-HYD");
	}
	@Bean
	Queue nisumPune() {
		return new Queue("nisum-PUNE");
	}
	@Bean
	Queue nisumVado() {
		return new Queue("nisum-VADO");
	}
	
	
	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange("fanout-exchange");
	}
	
	
	@Bean
	Binding hydBranchBinding(Queue nisumHyd,FanoutExchange exchange) {
		return BindingBuilder.bind(nisumHyd).to(exchange);
	}
	@Bean
	Binding puneBranchBinding(Queue nisumPune,FanoutExchange exchange) {
		return BindingBuilder.bind(nisumPune).to(exchange);
	}
	@Bean
	Binding vadoBranchBinding(Queue nisumVado,FanoutExchange exchange) {
		return BindingBuilder.bind(nisumVado).to(exchange);
	}
	
	
	@Bean
	public MessageConverter jSONmessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	
	@Bean
	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
		return simpleMessageListenerContainer;
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jSONmessageConverter());
		return rabbitTemplate;
	}
	
}
