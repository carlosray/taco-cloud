package tacos.model.messages.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tacos.model.Order;
import tacos.model.messages.OrderMessagingService;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

    private KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(Order order) {
        //send message to default topic (set in properties)
        //kafkaTemplate.sendDefault(order);
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }
}
