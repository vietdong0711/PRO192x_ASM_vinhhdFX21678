package vn.funix.FX21678.asm03.models;

import java.util.UUID;

public class Transaction {

    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private String status;

    public Transaction(String accountNumber, double amount, String time, String status){
        this.id = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
