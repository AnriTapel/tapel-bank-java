package hello.entities;

import javax.persistence.*;

/**
 * Created by ANRI on 14.05.2019.
 */
@Entity
public class Accounts implements Comparable< Accounts >{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int clientId;
    @Column(unique = true)
    private String account;
    private String cvc;
    private double balance;

    public Accounts(){
    }

    public Accounts(int clientId, String account, String cvc, double balance) {
        this.clientId = clientId;
        this.account = account;
        this.cvc = cvc;
        this.balance = balance;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", account='" + account + '\'' +
                ", cvc='" + cvc + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public int compareTo(Accounts o) {
        return this.getAccount().compareTo(String.valueOf(o.getBalance()));
    }
}
