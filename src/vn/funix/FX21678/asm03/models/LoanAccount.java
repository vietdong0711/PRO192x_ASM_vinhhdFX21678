package vn.funix.FX21678.asm03.models;

import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm03.utils.Utils;

public class LoanAccount extends Account implements Withdraw, ReportService {

    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;

    public LoanAccount(){

    }

    public LoanAccount(String accountNumber){
        this.setAccountNumber(accountNumber);
    }

    public static String getTitle(){
        return "BIEN LAI GIAO DICH";
    }

    @Override
    public boolean withdraw(double amount) {
        double newBalance = 0.0;
        if (isAccepted(newBalance)){
            setBalance(newBalance);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {

        return false;
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

    public boolean withdraw(String accountNumber, double amount){

        return true;
    }
}
