package rabbitmq.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rabbitmq.consumer.listener.Consumer;

@RestController
@RequestMapping(value = "/rabbitmq-listener")
public class ConsumerController {
//	
//	@Autowired
//	Consumer consumer;
//	
//	@GetMapping(value = "/consume/{queueName}")
//	public String  consumeMessagesFromRabbitMQQueues(@PathVariable String queueName) {
//		
//		List<String> messages = consumer.messagesFromQUEUE(queueName);
//		
//		messages.forEach(msg->System.out.println("message from "+consumer.QUEUENAME+":"+msg));
//		return "check console";
//	}

}
