package tacos.model.messages.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacos.model.Order;
import tacos.model.messages.OrderReceiver;

@Service
public class RabbitOrderReceiver implements OrderReceiver {
    private RabbitTemplate rabbitTemplate;
    private MessageConverter messageConverter;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = rabbitTemplate.getMessageConverter();
    }

    @Override
    public Order receiveOrder() {
        Message message = rabbitTemplate.receive("tacocloud.order");
        return message != null ? (Order) messageConverter.fromMessage(message) : null;
    }
}
