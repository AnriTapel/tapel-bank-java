package hello.entities;

import javax.persistence.*;

/**
 * Created by Xiaomi on 17.06.2019.
 */
@Entity
public class Authorities {

    @Id
    @Column(unique = true)
    private String username;
    private String authority;
    private int clientId;

    public Authorities(){
    }

    public Authorities(String username, String authority, int clientId) {
        this.username = username;
        this.authority = authority;
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
