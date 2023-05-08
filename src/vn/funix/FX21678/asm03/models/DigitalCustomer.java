package vn.funix.FX21678.asm03.models;

import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm02.models.Customer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DigitalCustomer extends Customer {

    @Override
    public void displayInformation() {
        Locale locale = new Locale("vi", "VI");
        String pattern = "###,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);
        double tong = 0;
        for (Account acc : this.getAccounts()) {
            tong += acc.getBalance();
        }

        System.out.printf("%-18s|%-18s|%-18s|%-18s\n", this.getCustomerId(), this.getName(), this.isPremium() ? "Premium" : "Normal", dcf.format(tong));
        int i = 1;
        for (Account acc : this.getAccounts()) {
            System.out.printf("%-3s%-15%s|-18%s|-37s\n", i, acc.getAccountNumber(), "123", dcf.format(acc.getBalance()));
            i++;
        }
    }
}
