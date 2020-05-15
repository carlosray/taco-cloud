package tacos.integration.email;

import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import tacos.model.Order;

import javax.mail.Message;

@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<Order> {


    @Override
    protected AbstractIntegrationMessageBuilder<Order> doTransform(Message message) throws Exception {
        //parse message and return parsed order
        return MessageBuilder.withPayload(new Order());
    }
}
