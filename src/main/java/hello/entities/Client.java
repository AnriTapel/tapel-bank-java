package hello.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ANRI on 13.05.2019.
 */

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String clientName;
    private String clientLastname;
    @Column(unique = true)
    private String clientPassport;
    private Date birthday;
    private String clientPhone;

    public Client(){
    }

    public Client(String clientName, String clientLastname, String clientPassport, Date birthday, String clientPhone) {
        this.clientName = clientName;
        this.clientLastname = clientLastname;
        this.clientPassport = clientPassport;
        this.birthday = birthday;
        this.clientPhone = clientPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastname() {
        return clientLastname;
    }

    public void setClientLastname(String clientLastname) {
        this.clientLastname = clientLastname;
    }

    public String getClientPassport() {
        return clientPassport;
    }

    public void setClientPassport(String clientPassport) {
        this.clientPassport = clientPassport;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientLastname='" + clientLastname + '\'' +
                ", clientPassport='" + clientPassport + '\'' +
                ", birthday=" + birthday +
                ", clientPhone='" + clientPhone + '\'' +
                '}';
    }
}