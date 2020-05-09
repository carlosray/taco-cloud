package tacos.model.messages.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tacos.model.Order;

@Component
public class RabbitOrderListener {

    @RabbitListener(queues = "tacocloud.order")
    public void receiveOrder(Order order) {
        System.out.println(order);
    }
}
