#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import dk.emstar.common.spring.Environments;

@PropertySource({"file:${symbol_dollar}{config_home}/springcxf/prod/environment.properties"})
@Configuration
@Profile(Environments.PROD)
public class ProdEnvironmentSpringConfig {

}
