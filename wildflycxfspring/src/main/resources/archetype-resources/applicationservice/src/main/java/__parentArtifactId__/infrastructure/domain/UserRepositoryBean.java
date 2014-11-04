#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.infrastructure.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ${package}.${parentArtifactId}.domain.User;
import ${package}.${parentArtifactId}.services.domain.UserRepository;

@Repository("userRepository")
public class UserRepositoryBean implements UserRepository {

    @Autowired
    private JpaTransactionManager txManager;
    
    @PersistenceContext(name="test")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void createUserBMT(User user) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("rootTransaction");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        entityManager.persist(user);
        txManager.commit(status);
    }

}

