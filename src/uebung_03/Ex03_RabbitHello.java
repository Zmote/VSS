package uebung_03;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Ex03_RabbitHello {
	private final static String QUEUE_NAME = "hello_queue";
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = createConnection();
		Channel channel = createChannel(connection);
		publishMessage(channel, args);
	}
	private static void publishMessage(Channel channel, String[] args) throws UnsupportedEncodingException, IOException {
		String message = getMessage(args);
		channel.basicPublish("", QUEUE_NAME,null, message.getBytes("UTF-8"));
		System.out.printf(" [x] Sent '%s\n",message);
	}
	
	private static String getMessage(String[] args) {
		if(args.length < 1){
			return "Hello World";
		}
		return args[0];
	}
	private static Channel createChannel(Connection connection) throws IOException {
		Channel channel = connection.createChannel();
		boolean durable = false;
		boolean exclusive = false;
		boolean autoDelete = false;
		Map<String, Object> queueProperties = null;
		channel.queueDeclare(QUEUE_NAME, durable,exclusive,autoDelete,queueProperties);
		return channel;
	}
	private static Connection createConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		return factory.newConnection();
	}

}
