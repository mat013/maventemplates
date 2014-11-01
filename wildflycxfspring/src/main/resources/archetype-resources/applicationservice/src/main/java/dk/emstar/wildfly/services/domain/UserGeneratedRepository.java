#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.services.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import dk.emstar.wildfly.domain.User;

public interface UserGeneratedRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
}