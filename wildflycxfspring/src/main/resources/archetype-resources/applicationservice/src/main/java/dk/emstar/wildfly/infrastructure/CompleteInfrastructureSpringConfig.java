#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import dk.emstar.common.spring.Environments;

@Configuration
@Profile({Environments.PROD, Environments.PREPROD, Environments.TEST, Environments.DEV, Environments.LOCAL})
public class CompleteInfrastructureSpringConfig {
}
