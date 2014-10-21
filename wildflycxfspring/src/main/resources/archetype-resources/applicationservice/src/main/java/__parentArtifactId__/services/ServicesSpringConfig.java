#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ${package}.${parentArtifactId}.services.domain.UserGeneratedRepository;

@ComponentScan(basePackageClasses = {ServicesSpringConfig.class})
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory", 
    transactionManagerRef = "transactionManagerJPA", 
    basePackageClasses={ UserGeneratedRepository.class })
public class ServicesSpringConfig {

}
