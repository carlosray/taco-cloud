package tacos.model.messages;

import tacos.model.Order;

public interface OrderReceiver {
    Order receiveOrder();
}
