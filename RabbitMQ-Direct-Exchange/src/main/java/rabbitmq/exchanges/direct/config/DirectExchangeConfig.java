package rabbitmq.exchanges.direct.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
public class DirectExchangeConfig {

	private static final String HYDERABAD_ROUTING_KEY = "hyderabad";
	private static final String PUNE_ROUTING_KEY = "pune";
	private static final String VADO_ROUTING_KEY = "vadodhara";
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
	DirectExchange exchange() {
		return new DirectExchange("direct-exchange");
	}
	
	@Bean
	Binding hydBranchBinding(Queue nisumHyd,DirectExchange exchange) {
		return BindingBuilder.bind(nisumHyd).to(exchange).with(HYDERABAD_ROUTING_KEY);
	}
	@Bean
	Binding puneBranchBinding(Queue nisumPune,DirectExchange exchange) {
		return BindingBuilder.bind(nisumPune).to(exchange).with(PUNE_ROUTING_KEY);
	}
	@Bean
	Binding vadoBranchBinding(Queue nisumVado,DirectExchange exchange) {
		return BindingBuilder.bind(nisumVado).to(exchange).with(VADO_ROUTING_KEY);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
		return simpleMessageListenerContainer;
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}