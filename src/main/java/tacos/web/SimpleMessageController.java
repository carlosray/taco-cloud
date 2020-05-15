package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tacos.model.Order;
import tacos.messages.OrderMessagingService;
import tacos.messages.OrderReceiver;
import tacos.repo.OrderRepository;

@Controller
@RequestMapping("/message/")
public class SimpleMessageController {

    private OrderRepository orderRepository;
    private OrderMessagingService sender;
    private OrderReceiver receiver;

    @Autowired
    public SimpleMessageController(OrderRepository orderRepository, @Qualifier("rabbitOrderMessagingService") OrderMessagingService sender, @Qualifier("rabbitOrderReceiver") OrderReceiver receiver) {
        this.orderRepository = orderRepository;
        this.sender = sender;
        this.receiver = receiver;
    }

    @GetMapping("/order/send")
    @ResponseBody
    public String sendOrderMessage(@RequestParam String name) {
        Order order = orderRepository.findOrderByName(name);
        if (order == null) return "order is null";
        sender.sendOrder(order);
        return "Successfully send order: " + order.getName();
    }

    @GetMapping("/order/receive")
    @ResponseBody
    public String receiveOrderMessage() {
        Order order = receiver.receiveOrder();
        if (order != null) {
            return "Successfully receive order: " + order.getName();
        }
        else {
            return "Order is null (doesn`t exist in queue)";
        }
    }
}
