package vn.funix.FX21678.asm03.models;

import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm03.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount extends Account implements ReportService, Withdraw {

    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public SavingsAccount() {

    }

    public SavingsAccount(String accountNumber, double balance) {
        this.setAccountNumber(accountNumber);
        this.setBalance(balance);
    }

    public void addTransaction(double amount, String status) {
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction(getAccountNumber(), amount, date.toString(), status);
        transactions.add(transaction);
    }

    public static String getTitle() {
        return "BIEN LAI GIAO DICH";
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", getTitle());
        System.out.printf("Ngay G/D: %30s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("So TK: %31s%n", this.getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount) + "đ");
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(this.getBalance()) + "đ");
        System.out.printf("PHI + VAT: %27s%n", "0đ");
        System.out.println(Utils.getDivider());
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            setBalance(this.getBalance() - amount);
            addTransaction(amount, "DONE");
            log(amount);
            return true;
        }
        addTransaction(amount, "FAIL");
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        return ((amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW && !this.isPremium()) || this.isPremium()) && this.getBalance() - amount >= 50000 && amount % 10000 == 0 && amount >= 50000;
    }
}
