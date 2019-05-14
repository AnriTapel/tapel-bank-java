package hello.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

/**
 * Created by ANRI on 13.05.2019.
 */

public class Client {

    private String client_name;
    private String client_lastname;
    private String birth_date;
    private String client_account;
    private String client_cvc;
    private String client_keyword;
    private String client_phone;
    private int client_balance;

    public Client(String client_name, String client_lastname, String birth_date, String client_account, String client_cvc, String client_keyword, String client_phone, int client_balance) {
        this.client_name = client_name;
        this.client_lastname = client_lastname;
        this.birth_date = birth_date;
        this.client_account = client_account;
        this.client_cvc = client_cvc;
        this.client_keyword = client_keyword;
        this.client_phone = client_phone;
        this.client_balance = client_balance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_name='" + client_name + '\'' +
                ", client_lastname='" + client_lastname + '\'' +
                ", birth_date=" + birth_date +
                ", client_account='" + client_account + '\'' +
                ", client_cvc='" + client_cvc + '\'' +
                ", client_keyword='" + client_keyword + '\'' +
                ", client_phone='" + client_phone + '\'' +
                ", client_balance=" + client_balance +
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

    public String getClient_account() {
        return client_account;
    }

    public void setClient_account(String client_account) {
        this.client_account = client_account;
    }

    public String getClient_cvc() {
        return client_cvc;
    }

    public void setClient_cvc(String client_cvc) {
        this.client_cvc = client_cvc;
    }

    public String getClient_keyword() {
        return client_keyword;
    }

    public void setClient_keyword(String client_keyword) {
        this.client_keyword = client_keyword;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public int getClient_balance() {
        return client_balance;
    }

    public void setClient_balance(int client_balance) {
        this.client_balance = client_balance;
    }
}
