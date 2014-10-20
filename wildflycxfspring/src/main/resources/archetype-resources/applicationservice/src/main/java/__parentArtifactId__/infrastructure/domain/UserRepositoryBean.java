#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.infrastructure.domain;

import org.springframework.stereotype.Component;

import ${package}.${parentArtifactId}.services.domain.UserRepository;

@Component
public class UserRepositoryBean implements UserRepository {

}
