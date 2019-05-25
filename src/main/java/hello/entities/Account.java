package hello.entities;

/**
 * Created by ANRI on 14.05.2019.
 */

public class Account {

    private int id;

    private String client_account;
    private String client_cvc;
    private String client_keyword;
    private double client_balance;

    public Account(String client_account, String client_cvc, String client_keyword, double client_balance) {
        this.client_account = client_account;
        this.client_cvc = client_cvc;
        this.client_keyword = client_keyword;
        this.client_balance = client_balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getClient_balance() {
        return client_balance;
    }

    public void setClient_balance(double client_balance) {
        this.client_balance = client_balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client_account='" + client_account + '\'' +
                ", client_cvc='" + client_cvc + '\'' +
                ", client_keyword='" + client_keyword + '\'' +
                ", client_balance=" + client_balance +
                '}';
    }
}
