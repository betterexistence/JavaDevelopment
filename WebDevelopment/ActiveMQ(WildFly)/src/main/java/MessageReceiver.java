import org.apache.activemq.ActiveMQConnection;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageReceiver {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public static void main(String[] args) {
        try {
            Context context = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
            Destination queue = (Destination) context.lookup("Queue");

            try(JMSContext jmsContext = connectionFactory.createContext()){
                while (true){
                    String message = jmsContext.createConsumer(queue).receiveBody(String.class);
                    System.out.println(message);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public MessageReceiver() throws JMSException{

        try {
            Context context = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
            Destination queue = (Destination) context.lookup("Queue");

            try(JMSContext jmsContext = connectionFactory.createContext()){
                while (true){
                    String message = jmsContext.createConsumer(queue).receiveBody(String.class);
                    System.out.println(message);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
//        Context context = new InitialContext();
//        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
//        Destination queue = (Destination) context.lookup("Queue");
//        Connection connection = connectionFactory.createConnection();
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        MessageConsumer consumer = session.createConsumer(queue);
//        connection.start();
//        while (true){
//            TextMessage message = (TextMessage) consumer.receive();
//            System.out.println(message.getText());
//        }

        //        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue(queueName);
//        MessageConsumer consumer = session.createConsumer(destination);
//        Message message = consumer.receive();
//
//        if (message instanceof TextMessage) {
//            String item = ((TextMessage) message).getText();
//            if(item.matches("[a-zA-Z -]{2,10}") || item.matches("[а-яёА-ЯЁ -]{2,10}")){
//                System.out.println("agree");
//                setItem(item);
//            }else{
//                System.out.println("not agree");
//                setItem("!@#$");
//            }
//        }
//        session.close();
//        connection.close();
    }
}