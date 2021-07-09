package rabbitmq.consumer.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	Consumer(){}

  private static List<String> messages = new ArrayList<>();
  public static final String QUEUENAME = "nisum-HYD";
	
	@RabbitListener(queues = QUEUENAME)
	public void consumeNisumHYDQueue(String message) {
		messages.add(message);
	}

	public List<String> messagesFromQUEUE(String QueueName){
		System.out.println("need to think of pass this queuename to rabbitlistener");
		return this.messages;
	}
}
