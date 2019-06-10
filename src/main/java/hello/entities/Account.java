package hello.entities;

import javax.persistence.*;

/**
 * Created by ANRI on 14.05.2019.
 */
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int clientId;
    @Column(unique = true)
    private String clientAccount;
    private String clientCvc;
    private String clientKeyword;
    private double clientBalance;

    public Account(){
    }

    public Account(int clientId, String clientAccount, String clientCvc, String clientKeyword, double clientBalance) {
        this.clientId = clientId;
        this.clientAccount = clientAccount;
        this.clientCvc = clientCvc;
        this.clientKeyword = clientKeyword;
        this.clientBalance = clientBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(String clientAccount) {
        this.clientAccount = clientAccount;
    }

    public String getClientCvc() {
        return clientCvc;
    }

    public void setClientCvc(String clientCvc) {
        this.clientCvc = clientCvc;
    }

    public String getClientKeyword() {
        return clientKeyword;
    }

    public void setClientKeyword(String clientKeyword) {
        this.clientKeyword = clientKeyword;
    }

    public double getClientBalance() {
        return clientBalance;
    }

    public void setClientBalance(double clientBalance) {
        this.clientBalance = clientBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", clientAccount='" + clientAccount + '\'' +
                ", clientCvc='" + clientCvc + '\'' +
                ", clientKeyword='" + clientKeyword + '\'' +
                ", clientBalance=" + clientBalance +
                '}';
    }
}
