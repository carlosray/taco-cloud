package tacos.model.messages.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tacos.model.Order;

@Component
public class OrderListener {

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        System.out.println("received order: " + order);
    }
}
