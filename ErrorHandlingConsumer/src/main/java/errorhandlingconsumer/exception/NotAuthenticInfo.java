package errorhandlingconsumer.exception;

public class NotAuthenticInfo extends Exception{
	private static final long serialVersionUID = -3154618962130084535L;
	
	public NotAuthenticInfo() throws InterruptedException{
		System.out.print("Message has no authentic signature NISUM, retrying");
		for(int i=0;i<5;i++) {
			Thread.sleep(800);
			System.out.print(".");
		}
		System.out.println();
	}
}
