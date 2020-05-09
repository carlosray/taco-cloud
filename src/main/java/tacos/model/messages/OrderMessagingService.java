package tacos.model.messages;

import tacos.model.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
