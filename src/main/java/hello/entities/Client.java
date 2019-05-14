package hello.entities;

import javax.persistence.*;

/**
 * Created by ANRI on 13.05.2019.
 */

@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String client_name;
    private String client_lastname;
    private String birth_date;
    private String client_phone;




    public Client(String client_name, String client_lastname, String birth_date, String client_account, String client_cvc, String client_keyword, String client_phone, int client_balance) {
        this.client_name = client_name;
        this.client_lastname = client_lastname;
        this.birth_date = birth_date;
        this.client_phone = client_phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_name='" + client_name + '\'' +
                ", client_lastname='" + client_lastname + '\'' +
                ", birth_date=" + birth_date +
                ", client_phone='" + client_phone + '\'' +
                '}';
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_lastname() {
        return client_lastname;
    }

    public void setClient_lastname(String client_lastname) {
        this.client_lastname = client_lastname;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

}
