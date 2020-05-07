package tacos.model.messages.jms;

import tacos.model.Order;

public interface OrderReceiver {
    Order receiveOrder();
}
