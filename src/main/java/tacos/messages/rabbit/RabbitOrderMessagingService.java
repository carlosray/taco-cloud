package tacos.messages.rabbit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacos.model.Order;
import tacos.messages.OrderMessagingService;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        /*MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        properties.setHeader("X_ORDER_SOURCE", "WEB");
        Message message = messageConverter.toMessage(order, properties);
        rabbitTemplate.send("tacocloud.order", message);*/
        rabbitTemplate.convertAndSend("tacocloud.order", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties properties = message.getMessageProperties();
                properties.setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            }
        });
    }
}
