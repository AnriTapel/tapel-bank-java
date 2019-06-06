package hello.entities;

import javax.annotation.Generated;
import java.util.Date;

/**
 * Created by ANRI on 13.05.2019.
 */

public class Client {

    private int id;

    private String client_name;
    private String client_lastname;
    private String client_passport;
    private Date birth_date;
    private String client_phone;

    public Client(int id, String client_name, String client_lastname, String client_passport, Date birth_date, String client_phone) {
        this.id = id;
        this.client_name = client_name;
        this.client_lastname = client_lastname;
        this.client_passport = client_passport;
        this.birth_date = birth_date;
        this.client_phone = client_phone;
    }

    public Client(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getClient_passport() {
        return client_passport;
    }

    public void setClient_passport(String client_passport) {
        this.client_passport = client_passport;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", client_name='" + client_name + '\'' +
                ", client_lastname='" + client_lastname + '\'' +
                ", client_passport='" + client_passport + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", client_phone='" + client_phone + '\'' +
                '}';
    }
}