package uebung_03;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class Ex03_PrimeMessageProducer {
    private static final String TASK_QUEUE_NAME = "prime_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //damit persistiert werden
        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

        //TODO Ex03_PrimeCounterMessage erstellen mit dem Zahlenbereich aus argv[0]-argv[1]
        int start = Integer.parseInt(argv[0]);
        int end = Integer.parseInt(argv[1]);
        Ex03_PrimeCounterMessage message = new Ex03_PrimeCounterMessage(start,end);
        //TODO und die Message publizieren
        channel.basicPublish("", TASK_QUEUE_NAME,
        		MessageProperties.PERSISTENT_TEXT_PLAIN,
        		message.toBytes());
        System.out.printf(" [x] Sent '&s'\n", message.toString());
        channel.close();
        connection.close();
    }
}