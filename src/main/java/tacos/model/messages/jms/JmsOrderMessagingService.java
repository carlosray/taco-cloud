package tacos.model.messages.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.model.Order;
import tacos.model.messages.OrderMessagingService;

import javax.jms.Destination;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jms;
    private Destination destination;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, Destination destination) {
        this.jms = jms;
        this.destination = destination;
    }

    @Override
    public void sendOrder(Order order) {
        //jms.send(destination, session -> session.createObjectMessage(order));
        jms.convertAndSend(destination, order, message -> {
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }
}
