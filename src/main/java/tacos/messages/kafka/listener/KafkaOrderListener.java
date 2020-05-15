package tacos.messages.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import tacos.model.Order;

@Component
public class KafkaOrderListener {

    //@KafkaListener(topics = "tacocloud.orders.topic")
    public void receiveOrder(Order order, ConsumerRecord<String, Order> record) {
        System.out.println("received order: " + order);
    }
}
