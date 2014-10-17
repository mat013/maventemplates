#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId};

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import dk.emstar.common.CommonSpringConfig;
import ${package}.${parentArtifactId}.infrastructure.InfrastructureSpringConfig;
import ${package}.${parentArtifactId}.services.ServicesSpringConfig;

@Import({CommonSpringConfig.class,
    InfrastructureSpringConfig.class, 
    ServicesSpringConfig.class})
@Configuration
public class MainSpringConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
       return propertySourcesPlaceholderConfigurer;
    }
}
