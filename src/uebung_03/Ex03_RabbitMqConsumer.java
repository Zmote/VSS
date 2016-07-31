package uebung_03;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Ex03_RabbitMqConsumer {
    private final static String QUEUE_NAME = "hello_queue";

    public static void main(String[] argv) throws Exception {
        Connection connection = createConnection();
        Channel channel = createChannel(connection);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C.");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        }
    }

    private static Channel createChannel(Connection connection) throws IOException {
        Channel channel = connection.createChannel();
        boolean durable = false;
        boolean exclusive = false;
        boolean autoDelete = false;
        Map<String, Object> queueProperties = null;
        channel.queueDeclare(QUEUE_NAME, durable, exclusive, autoDelete, queueProperties);
        return channel;
    }

    private static Connection createConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        return factory.newConnection();
    }
}