#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.domain;

import org.springframework.data.repository.CrudRepository;

import ${package}.domain.User;

public interface UserGeneratedRepository extends CrudRepository<User, Long> {
}