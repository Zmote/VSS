package uebung_03;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Ex03_TwitterMessageTopicConsumer {
    private static final String EXCHANGE_NAME = "twitter_topics";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //TODO Erstelle nameless queue
        String queueName = channel.queueDeclare().getQueue();

        for(String topic : getTopics(argv)) {
            //TODO Binde queue und exchange
        	System.out.printf(" [*] bound to topic '%s'\n",topic);
        	channel.queueBind(queueName, EXCHANGE_NAME, topic);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //TODO basicConsume
        channel.basicConsume(queueName, true,consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            //TODO Finde Topic von delivery heraus
            String topic = delivery.getEnvelope().getRoutingKey();
            System.out.format(" [x] Received '%s': '%s'\n",topic,message);
        }
    }

    private static String[] getTopics(String[] argv) {
        if (argv.length < 1)
            return new String[] { "boris.*" };
        return argv;
    }
}