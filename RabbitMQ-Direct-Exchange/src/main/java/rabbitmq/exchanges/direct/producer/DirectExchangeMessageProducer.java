package rabbitmq.exchanges.direct.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/direct-exchange/")
public class DirectExchangeMessageProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@GetMapping("/produce")
	public String producer(@RequestParam("exchangeName") String exchangeName,@RequestParam("routingKey") String routingKey,
			@RequestParam("message") String message) {

		amqpTemplate.convertAndSend(exchangeName, routingKey, message);
		
		return "DirectExchange : message sent successfully to Queue that bind with " + routingKey+ " check dashboard !!";
	}
}