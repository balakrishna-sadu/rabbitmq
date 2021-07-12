package errorhandlingproducer.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "error-handling/")
public class ProducerController {

	@Autowired
	RabbitTemplate rabbitMq;
	@GetMapping("/produce")
	public String produceMessage(@RequestParam("message") String message,@RequestParam("exchange") String exchange,@RequestParam("routingKey") String routingKey) {
		rabbitMq.convertAndSend(exchange,routingKey,message);
		return "message produced successfully";
	}
	
}
