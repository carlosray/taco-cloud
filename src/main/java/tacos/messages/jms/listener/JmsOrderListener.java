package tacos.messages.jms.listener;

import org.springframework.stereotype.Component;
import tacos.model.Order;

@Component
public class JmsOrderListener{

    //@JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        System.out.println("received order: " + order);
    }
}
