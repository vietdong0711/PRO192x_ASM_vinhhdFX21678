package vn.funix.FX21678.asm02.models;

import vn.funix.FX21678.asm03.models.Transaction;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Account {
    private String accountNumber;
    private double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(){

    }

    public Account(String accountNumber, double balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean isPremium() {
        if (this.balance < 10000000)
            return false;
        return true;
    }

    public String toString(){
        Locale locale = new Locale("vi", "VI");
        String pattern = "###,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);

        String accountString = "\t\t    "+this.accountNumber+"|\t\t\t\t                       "+ dcf.format(this.balance);
        return accountString;
    }
}
