package uebung_03;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class Ex03_TwitterMessageConsumer {
    private static final String EXCHANGE_NAME = "twitter";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //TODO Deklariere exchange
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //TODO Erstellen einer nameless queue
        String queueName = channel.queueDeclare().getQueue();
        //TODO Binde queue und exchange
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true,consumer);
        
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.format(" [x] Received '%s'\n", message);
        }
    }
}