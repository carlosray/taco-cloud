package tacos.model.messages.jms;

import tacos.model.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
