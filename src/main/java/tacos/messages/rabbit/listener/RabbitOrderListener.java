package tacos.messages.rabbit.listener;

import org.springframework.stereotype.Component;
import tacos.model.Order;

@Component
public class RabbitOrderListener {

    //@RabbitListener(queues = "tacocloud.order")
    public void receiveOrder(Order order) {
        System.out.println(order);
    }
}
