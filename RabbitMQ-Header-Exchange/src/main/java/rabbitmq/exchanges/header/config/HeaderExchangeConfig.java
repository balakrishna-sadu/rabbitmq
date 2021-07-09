package rabbitmq.exchanges.header.config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
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
public class HeaderExchangeConfig {

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
	HeadersExchange headerExchange() {
		return new HeadersExchange("headers-exchange");
	}

	
	@Bean
	Binding hyderabadBranchBinding( Queue nisumHyd , HeadersExchange headerExchange) {
		return BindingBuilder.bind(nisumHyd).to(headerExchange).where("branch").matches("hyderabad");
	}
	
	@Bean
	Binding puneBranchBinding(Queue nisumPune, HeadersExchange headersExchange) {
		return BindingBuilder.bind(nisumPune).to(headersExchange).where("branch").matches("pune");
	}
	@Bean
	Binding vadodharaBranchBinding(Queue nisumVado, HeadersExchange headersExchange) {
		return BindingBuilder.bind(nisumVado).to(headersExchange).where("branch").matches("vadodhara");
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