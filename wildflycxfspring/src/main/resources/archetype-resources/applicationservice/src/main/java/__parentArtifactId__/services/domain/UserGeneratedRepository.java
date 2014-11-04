#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.services.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import ${package}.${parentArtifactId}.domain.User;

public interface UserGeneratedRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
}