package vn.funix.FX21678.asm03;

import vn.funix.FX21678.asm02.models.Customer;
import vn.funix.FX21678.asm03.models.DigitalBank;

import java.util.Scanner;

public class Asm03 {
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "001215000001";
    private static final String CUSTOMER_NAME = "FUNIX";



    private static void showCustomer(){
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null)
            customer.displayInformation();

    }

    public static void main(String[] args) {
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
                    break;
                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;

                default:
                    System.out.println("Vui long nhap lai: ");

            }

        }
    }
}
