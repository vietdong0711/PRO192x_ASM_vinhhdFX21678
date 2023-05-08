package vn.funix.FX21678.asm02.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Customer extends User {

    List<Account> accounts;

    public  Customer(){
        accounts = new ArrayList<>();
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean isPremium() {
        for (Account acc : accounts) {
            if (acc.isPremium())
                return true;
        }
        return false;
    }

    public void addAccount(Account newAccount) {
        if (accounts.contains(newAccount.getAccountNumber()))
            accounts.add(newAccount);
        else
            System.out.println("STK da dc su dung");
    }

    public double getBalance() {
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }

    public void displayInformation() {
        Locale locale = new Locale("vi", "VI");
        String pattern = "###,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);

        System.out.printf("%-18s|%-18s|%-18s|%-18s\n", this.getCustomerId(), this.getName(), this.isPremium()?"Premium":"Normal", dcf.format(this.getBalance()));
        int i = 1;
        for (Account acc : accounts) {
            System.out.printf("%-3s%-15%s|-56s\n", i, acc.getAccountNumber(),  dcf.format(acc.getBalance())+"đ");
            i++;
        }
    }


}
