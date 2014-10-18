#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import dk.emstar.common.aspect.CorrelationIdWebserviceAspect;

@ComponentScan(basePackageClasses = {CommonSpringConfig.class})
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class CommonSpringConfig {
    
    @Bean
    public CorrelationIdWebserviceAspect loggingWebServiceSystemBoundaryAspect() {
        return new CorrelationIdWebserviceAspect();
    }
}
