package vn.funix.FX21678.asm03.models;

import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm03.utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoanAccount extends Account implements Withdraw, ReportService {
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;

    public LoanAccount() {

    }

    public LoanAccount(String accountNumber) {
        this.setAccountNumber(accountNumber);
    }

    public static String getTitle() {
        return "BIEN LAI GIAO DICH";
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            setBalance(amount + (this.isPremium() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LOAN_ACCOUNT_WITHDRAW_FEE) + getBalance());
            log(amount);
            addTransaction(amount, "DONE");
            return true;
        }
        addTransaction(amount, "FAIL");
        return false;
    }

    public void addTransaction(double amount, String status) {
        LocalDateTime date = LocalDateTime.now();
        Transaction transaction = new Transaction(getAccountNumber(), amount, date.toString(), status);
        transactions.add(transaction);
    }

    @Override
    public boolean isAccepted(double amount) {
        double tong = amount + (this.isPremium() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LOAN_ACCOUNT_WITHDRAW_FEE);
        return LOAN_ACCOUNT_MAX_BALANCE - this.getBalance() - tong >= 50000;
    }


    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", getTitle());
        System.out.printf("Ngay G/D: %30s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("So TK: %31s%n", this.getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(LOAN_ACCOUNT_MAX_BALANCE - getBalance() - amount - (this.isPremium() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LOAN_ACCOUNT_WITHDRAW_FEE)));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(this.getBalance() + (this.isPremium() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LOAN_ACCOUNT_WITHDRAW_FEE) + amount));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance((this.isPremium() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LOAN_ACCOUNT_WITHDRAW_FEE)));
        System.out.println(Utils.getDivider());
    }
}
