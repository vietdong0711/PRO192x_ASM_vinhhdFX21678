package vn.funix.FX21678.asm03;

import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm02.models.Customer;
import vn.funix.FX21678.asm03.models.DigitalBank;
import vn.funix.FX21678.asm03.models.DigitalCustomer;
import vn.funix.FX21678.asm03.models.LoanAccount;
import vn.funix.FX21678.asm03.models.SavingsAccount;
import vn.funix.FX21678.asm03.models.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Asm03 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";


    public static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null)
            customer.displayInformation();

    }

    public static boolean checkInt(String string) {
        try {
            // check xem co ki tu nào khác số ko
            String[] cccds = string.split("");
            for (int i = 0; i < cccds.length; i++) {
                int so = Integer.parseInt(cccds[i]);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkExistAccountNumber(String accountNumber) {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        List<Account> accounts = customer.getAccounts();
        if (accounts.stream().filter(item -> item.getAccountNumber().equals(accountNumber)).findFirst().isPresent())
            return true;
        return false;
    }

    public static boolean validateAccount(String accountNumber) {
        return accountNumber.length() == 6 && checkInt(accountNumber);
    }

    public static void addAccountATM() {
        System.out.println("Nhap ma so tai khoan: ");
        String accountNumber = scanner.nextLine();
        if (!validateAccount(accountNumber) || checkExistAccountNumber(accountNumber)) {
            System.out.println("Sai số tài khoan");
            return;
        }

        System.out.println("Nhap so du: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        Account account = new SavingsAccount(accountNumber, balance);
        Customer customer = activeBank.getCustomers().stream().filter(item -> item.getCustomerId().equals(CUSTOMER_ID)).findFirst().get();
        customer.getAccounts().add(account);
    }

    public static void addAccountLoan() {
        System.out.println("Nhap ma so tai khoan: ");
        String accountNumber = scanner.nextLine();
        if (!validateAccount(accountNumber) || checkExistAccountNumber(accountNumber)) {
            System.out.println("Sai số tài khoan");
            return;
        }

        Account account = new LoanAccount(accountNumber);
        Customer customer = activeBank.getCustomers().stream().filter(item -> item.getCustomerId().equals(CUSTOMER_ID)).findFirst().get();
        customer.getAccounts().add(account);
    }

    public static void rutTien() {
        System.out.println("Nhap so tai khoan can rut: ");
        String accountNumber = scanner.nextLine();
        if (!validateAccount(accountNumber) || !checkExistAccountNumber(accountNumber)) {
            System.out.println("Sai số tài khoan");
            return;
        }
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        Optional<Account> account = customer.getAccounts().stream().filter(item -> item.getAccountNumber().equals(accountNumber)).findFirst();
        if (!account.isPresent()) {
            System.out.println("Sai số tài khoan");
            return;
        } else {
            System.out.println("Nhap so tien can rút: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            Account acc = account.get();
            if (acc instanceof SavingsAccount) {
                ((SavingsAccount) acc).withdraw(amount);
            } else {
                ((LoanAccount) acc).withdraw(amount);
            }
        }
    }

    public static void xemLichSu() {

        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        List<Account> accounts = customer.getAccounts();
        DigitalCustomer dc = new DigitalCustomer();
        dc.setAccounts(customer.getAccounts());
        dc.setName(customer.getName());
        dc.setCustomerId(customer.getCustomerId());

        dc.displayInformation();
        for (Account acc : accounts) {
            if (acc instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) acc;
                List<Transaction> transactions = savingsAccount.getTransactions();
                for (Transaction tr : transactions) {
                    if (tr.getStatus().equals("DONE"))
                        System.out.printf("[GD]%14s|%39s|%18s\n", tr.getAccountNumber(), tr.getAmount(), tr.getTime());
                }
            } else {
                LoanAccount savingsAccount = (LoanAccount) acc;
                List<Transaction> transactions = savingsAccount.getTransactions();
                for (Transaction tr : transactions) {
                    if (tr.getStatus().equals("DONE"))
                        System.out.printf("[GD]%14s|%39s|%18s\n", tr.getAccountNumber(), tr.getAmount(), tr.getTime());
                }
            }
        }
    }

    public static void main(String[] args) {
        // them customer vào list customer của DigitalBank
        Customer customer = new Customer();
        customer.setName(CUSTOMER_NAME);
        customer.setCustomerId(CUSTOMER_ID);
        activeBank.getCustomers().add(customer);

        while (true) {
            System.out.println("+----------+-------------------------+---------+");
            System.out.println("| NGAN HANG SO | FX21678@v3.0.0                |");
            System.out.println("+----------+-------------------------+---------+");
            System.out.printf("|%-46s|\n", "1. Thong tin khach hang");
            System.out.printf("|%-46s|\n", "2. Them tai khoan ATM");
            System.out.printf("|%-46s|\n", "3. Them tai khoan tin dung");
            System.out.printf("|%-46s|\n", "4. Rut tien");
            System.out.printf("|%-46s|\n", "5. Lich su giao dich");
            System.out.printf("|%-46s|\n", "0. Thoát");
            System.out.println("+----------+-------------------------+---------+");
            System.out.println("Chuc nang: ");
            String chon = scanner.nextLine();
            switch (chon) {
                case "0":
                    System.out.println("thoat");
                    return;

                case "1":
                    showCustomer();
                    break;

                case "2":
                    addAccountATM();
                    break;

                case "3":
                    addAccountLoan();
                    break;

                case "4":
                    rutTien();
                    break;

                case "5":
                    xemLichSu();
                    break;

                default:
                    System.out.println("Vui long nhap lai: ");

            }

        }
    }
}
