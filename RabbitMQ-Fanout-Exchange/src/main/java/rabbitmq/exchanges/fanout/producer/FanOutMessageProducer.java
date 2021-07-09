package rabbitmq.exchanges.fanout.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fanout-exchange")
public class FanOutMessageProducer {
	
	
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	@GetMapping("/produce")
	public String producer(@RequestParam("exchangeName") String exchangeName,@RequestParam("message") String message) {
		
		amqpTemplate.convertAndSend(exchangeName, "", message);
		
		return "message sent successfully to all queues , check dashboard !!";
		
	}

}
