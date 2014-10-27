#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.common.cxf.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dk.emstar.common.time.ClockService;

@Component
public class SimpleLoggingInterceptor extends AbstractPhaseInterceptor<Message> {

    
    private ClockService clockService;

    @Autowired
    public SimpleLoggingInterceptor(ClockService clockService) {
        super(Phase.RECEIVE);
        this.clockService = clockService;
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        Exchange exchange = message.getExchange();
        exchange.put("invocationTime", clockService.now());
    }

}
