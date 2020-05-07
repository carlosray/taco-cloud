package tacos.model.messages.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.model.Order;

import javax.jms.Destination;

@Service
public class JmsOrderReceiver  implements OrderReceiver{

    private Destination destination;
    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(Destination destination, JmsTemplate jms) {
        this.destination = destination;
        this.jms = jms;
    }

    @Override
    public Order receiveOrder() {
        /*Message receive = jms.receive(destination);
        if (receive != null) {
            try {
                return (Order) messageConverter.fromMessage(receive);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return null;*/
        return (Order) jms.receiveAndConvert(destination);
    }
}
