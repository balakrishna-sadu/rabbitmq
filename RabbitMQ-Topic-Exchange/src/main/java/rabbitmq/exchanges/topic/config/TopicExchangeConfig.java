package rabbitmq.exchanges.topic.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {
	
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
	Queue nisumIndia() {
		return new Queue("nisumIndia", false);
	}

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("topic-exchange");
	}
	
	@Bean
	Binding marketingBinding(Queue nisumHyd, TopicExchange topicExchange) {
		return BindingBuilder.bind(nisumHyd).to(topicExchange).with("nisum.hyderabad");
	}
	
	@Bean
	Binding financeBinding(Queue nisumPune, TopicExchange topicExchange) {
		return BindingBuilder.bind(nisumPune).to(topicExchange).with("nisum.pune");
	}
	
	@Bean
	Binding adminBinding(Queue nisumVado, TopicExchange topicExchange) {
		return BindingBuilder.bind(nisumVado).to(topicExchange).with("nisum.vadodhara");
	}
	
	@Bean
	Binding allBinding(Queue nisumIndia, TopicExchange topicExchange) {
		return BindingBuilder.bind(nisumIndia).to(topicExchange).with("nisum.*");
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
