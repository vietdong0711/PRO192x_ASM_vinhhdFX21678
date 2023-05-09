package vn.funix.FX21678.asm03.models;

import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm03.utils.Utils;

public class SavingsAccount extends Account implements ReportService, Withdraw {

    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    public SavingsAccount() {

    }

    public SavingsAccount(String accountNumber, double balance) {
        this.setAccountNumber(accountNumber);
        this.setBalance(balance);
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
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(this.getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(amount));
        System.out.println(Utils.getDivider());
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            setBalance(this.getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        return amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW && this.getBalance() - amount >= 50000 && amount % 10000 == 0;
    }
}
