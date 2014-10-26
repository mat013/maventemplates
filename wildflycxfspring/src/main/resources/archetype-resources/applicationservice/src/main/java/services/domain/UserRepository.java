#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services.domain;

import ${package}.domain.User;

public interface UserRepository {
    void createUser(User user);

    void createUserBMT(User user);
}