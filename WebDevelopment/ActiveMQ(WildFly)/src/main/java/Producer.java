import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Producer {

    //private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws NamingException, JMSException {

        try {
            Properties properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://hostname:61616");
            Context jndiContext = new InitialContext(properties);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory" );
            Destination queue = (Destination) jndiContext.lookup("Queue");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage("Сообщение отправлено");
            producer.send(message);
            connection.close();
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    public Producer(){
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue(queueName);
//        MessageProducer producer = session.createProducer(destination);
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//        TextMessage message = session.createTextMessage(item);
//        producer.send(message);
//        System.out.println("JCG printing@@ '" + message.getText() + "'");
//        session.close();
//        connection.close();
    }
}