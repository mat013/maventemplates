#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${parentArtifactId}.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
@SequenceGenerator(name = "user_id_generator",  sequenceName = "users_seq", allocationSize=5)
public class User implements Serializable {

    private static final long serialVersionUID = -6956783582634460641L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @Id
    @Column
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(name ="last_login")
    private LocalDateTime lastLogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }    
}

