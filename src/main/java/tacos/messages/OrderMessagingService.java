package tacos.messages;

import tacos.model.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
