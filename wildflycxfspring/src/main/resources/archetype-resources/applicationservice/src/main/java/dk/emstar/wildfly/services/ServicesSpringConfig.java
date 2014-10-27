#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import dk.emstar.wildfly.services.domain.UserGeneratedRepository;

@ComponentScan(basePackageClasses = {ServicesSpringConfig.class})
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory", 
    transactionManagerRef = "transactionManagerJPA", 
    basePackageClasses={ UserGeneratedRepository.class })
public class ServicesSpringConfig {

}
