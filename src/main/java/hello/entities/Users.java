package hello.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ANRI on 13.05.2019.
 */

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String lastname;
    private String username;
    private String password;
    private boolean enabled;
    @Column(unique = true)
    private String passport;
    private Date birthday;

    public Users(){
    }

    public Users(String name, String lastname, String username, String password, boolean enabled, String passport, Date birthday) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.passport = passport;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", passport='" + passport + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}