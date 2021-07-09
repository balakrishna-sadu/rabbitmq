package rabbitmq.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class TestListener {
	
	public static String branch="foo";
	
//	@RabbitListener(queues = "#{queueName.buildFor(\"foo\")}")
	
	@RabbitListener(queues = "#{queueName.buildFor(${TestListener.branch})}")
	public void listen(String msg) {
		System.out.println("message from testListener Queue : "+msg);
	}

}

@Component
class QueueName {
    public String buildFor(String branch) {
        return "nisum-"+branch;
    }
}
