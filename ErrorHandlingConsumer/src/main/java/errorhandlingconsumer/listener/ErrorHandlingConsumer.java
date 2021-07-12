package errorhandlingconsumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import errorhandlingconsumer.exception.NotAuthenticInfo;

@Component
public class ErrorHandlingConsumer {
	
	@RabbitListener(queues = "nisum.updates")
	public void listenQueue(String msg) throws NotAuthenticInfo, InterruptedException {
		if(msg.contains("nisum")==false) {
			throw new NotAuthenticInfo();
		}
		System.out.println("message received : "+msg);
	}

}
