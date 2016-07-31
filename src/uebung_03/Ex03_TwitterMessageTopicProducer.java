package uebung_03;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Ex03_TwitterMessageTopicProducer {
    private static final String EXCHANGE_NAME = "twitter_topics";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //TODO Deklariere exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //TODO Publiziere message von argv
        String topic = getTopic(argv);
        String message = getMessage(argv);
        channel.basicPublish(EXCHANGE_NAME, topic, null, message.getBytes());
        channel.close();
        connection.close();
    }

    private static String getTopic(String[] argv) {
        if (argv.length < 1)
            return "boris.wimbledon";
        return argv[0];
    }

    private static String getMessage(String[] argv) {
        if (argv.length < 2)
            return "What a tough match Cemil!";
        return argv[1];
    }
}