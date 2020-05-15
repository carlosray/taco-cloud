package tacos.integration.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class TacoOrderEmailIntegrationConfig {

//    @Bean
//    public MessageChannel emailChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    @InboundChannelAdapter(channel="file-channel", poller=@Poller(fixedDelay=properties))
//    public MailReceivingMessageSource mailReceivingMessageSource() {
//        return new MailReceivingMessageSource(new ImapMailReceiver(properties.getImapUrl()));
//    }

    @Bean
    public AtomicInteger integerSource() {
        return new AtomicInteger();
    }

    @Bean
    public IntegrationFlow tacoOrderEmailFlow(EmailProperties emailProperties,
                                              EmailToOrderTransformer transformer,
                                              OrderSubmitMessageHandler handler) {
        return IntegrationFlows
                .from(Mail.imapInboundAdapter(emailProperties.getImapUrl()),
                        s -> s.poller(Pollers.fixedDelay(emailProperties.getPollRate())))
                .transform(transformer)
                .handle(handler)
                .get();
    }
}
