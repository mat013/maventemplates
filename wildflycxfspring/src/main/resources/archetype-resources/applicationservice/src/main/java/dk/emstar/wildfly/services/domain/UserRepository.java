#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.wildfly.services.domain;

import dk.emstar.wildfly.domain.User;

public interface UserRepository {
    void createUser(User user);

    void createUserBMT(User user);
}