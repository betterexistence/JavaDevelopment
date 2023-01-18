import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQ {
    public static class ProducerMQ{
        public ProducerMQ(String item) {
            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
                Connection connection = connectionFactory.createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination dest = session.createQueue("Queue");
                MessageProducer producer = session.createProducer(dest);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                Message message = session.createTextMessage(item);
                producer.send(message);
                session.close();
                connection.close();
            }
            catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }
    }

    public static class ConsumerMQ implements ExceptionListener {
        public String outMsg;
        public ConsumerMQ() {
            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
                Connection connection = connectionFactory.createConnection();
                connection.start();
                connection.setExceptionListener(this);
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue("Queue");
                MessageConsumer consumer = session.createConsumer(destination);
                Message message = consumer.receive(1000);

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String item =  textMessage.getText();
                    //"[а-яёА-ЯЁ -]{2,10}"
                    if(item.matches("[a-zA-Z -]{2,10}") || item.matches("[а-яёА-ЯЁ -]{2,10}")){
                        System.out.println("good " + item);
                        outMsg = "printList";
                        System.out.println(getOutMsg());
                    }else{
                        System.out.println("bad " + item);
                        outMsg = "printError";
                        System.out.println(getOutMsg());
                    }
                } else {
                    System.out.println("producer not exist");
                }

                consumer.close();
                session.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }


        public String getOutMsg() {
            return outMsg;
        }

        public void setOutMsg(String outMsg) {
            this.outMsg = outMsg;
        }

        public synchronized void onException(JMSException ex) {
            System.out.println("JMS Exception occured.  Shutting down client.");
        }
    }
}
