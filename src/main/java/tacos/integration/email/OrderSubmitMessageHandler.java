package tacos.integration.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import tacos.model.Order;
import tacos.repo.OrderRepository;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<Order> {

    private OrderRepository orderRepository;

    @Autowired
    public OrderSubmitMessageHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Object handle(Order order, MessageHeaders messageHeaders) {
        orderRepository.save(order);
        return null;
    }

}
