package hello.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Xiaomi on 23.06.2019.
 */

@Entity
public class Operations implements Comparable< Operations >{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transaction_id;
    @Column(name="sender_id")
    private int senderId;
    @Column(name="sender_account")
    private String senderAccount;
    @Column(name="receiver_id")
    private int receiverId;
    @Column(name="receiver_account")
    private String receiverAccount;
    @Column(name="operation_sum")
    private double operationSum;
    @Column(name="date")
    private Date date;
    private String comment;

    public Operations(){
    }

    public Operations(int senderId, String senderAccount, int receiverId, String receiverAccount, double operationSum, Date date, String comment) {
        this.senderId = senderId;
        this.senderAccount = senderAccount;
        this.receiverId = receiverId;
        this.receiverAccount = receiverAccount;
        this.operationSum = operationSum;
        this.date = date;
        this.comment = comment;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public double getOperationSum() {
        return operationSum;
    }

    public void setOperationSum(double operationSum) {
        this.operationSum = operationSum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Operations{" +
                "transaction_id=" + transaction_id +
                ", senderId=" + senderId +
                ", senderAccount='" + senderAccount + '\'' +
                ", receiverId=" + receiverId +
                ", receiverAccount='" + receiverAccount + '\'' +
                ", operationSum=" + operationSum +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public int compareTo(Operations o) {
        return this.getDate().compareTo(o.getDate());
    }
}
