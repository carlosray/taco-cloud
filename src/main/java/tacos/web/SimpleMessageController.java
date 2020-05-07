package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tacos.model.Order;
import tacos.model.messages.jms.OrderMessagingService;
import tacos.repo.OrderRepository;

@Controller
@RequestMapping("/message/jms")
public class SimpleMessageController {

    private OrderRepository orderRepository;
    private OrderMessagingService sender;

    @Autowired
    public SimpleMessageController(OrderRepository orderRepository, OrderMessagingService sender) {
        this.orderRepository = orderRepository;
        this.sender = sender;
    }

    @GetMapping("/order/{name}")
    @ResponseBody
    public String sendOrderJms(@PathVariable String name) {
        Order order = orderRepository.findOrderByName(name);
        if (order == null) return "order is null";
        sender.sendOrder(order);
        return "Successfully send order: " + order.getName();
    }
}
