package rabbitmq.exchanges.header.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/headers-exchange/")
public class HeaderExchangeProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@GetMapping(value = "/produce")
	public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("branch") String branch,
			@RequestParam("messageData") String messageData) {

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("branch",branch);
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message message = messageConverter.toMessage(messageData, messageProperties);
		amqpTemplate.send(exchange, "", message);

		return "Message sent to the RabbitMQ Header Exchange Successfully";
	}
}