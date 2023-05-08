package vn.funix.FX21678.asm02;

import vn.funix.FX21678.asm01.Asm01;
import vn.funix.FX21678.asm02.models.Account;
import vn.funix.FX21678.asm02.models.Bank;
import vn.funix.FX21678.asm02.models.Customer;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Asm02 {
    private static Scanner sc = new Scanner(System.in);

    private static final Bank bank = new Bank();

    public static void themKhachHang() {
        System.out.print("Nhap ten khach hang: ");
        String name = sc.nextLine();
        System.out.print("Nhap so CCCD: ");
        Customer customer = new Customer();
        List<String> listCCCD = bank.getCustomers().stream().map(cus -> cus.getCustomerId()).collect(Collectors.toList());
        while (true) {
            String cccd = sc.nextLine();
            if (cccd.equals("No"))
                return;

            if (listCCCD.contains(cccd)) {
                System.out.println("So CCCD da ton tai. Nhap lai");
            }
            if (!Asm01.checkCCCD(cccd)) {
                System.out.println("So CCCD khong hop le.");
                System.out.println("Vui long nhap lai hoac 'No' de thoat.");
            } else {
                customer.setCustomerId(cccd);
                customer.setName(name);
                bank.getCustomers().add(customer);
                System.out.println("Da them khach hang " + cccd + " vao danh sach");
                return;
            }
        }
    }


    public static boolean checkSTK(String stk) {
        List<String> listCCCD = bank.getCustomers().stream().map(cus -> cus.getCustomerId()).collect(Collectors.toList());
        if (stk.length() != 6)
            return false;
        if (!Asm01.checkInt(stk)) {
            return false;
        }
        if (listCCCD.contains(stk))
            return false;
        return true;
    }

    public static void themTaiKhoanKhachHang() {
        System.out.print("Nhap so CCCD: ");
        List<String> listCCCD = bank.getCustomers().stream().map(cus -> cus.getCustomerId()).collect(Collectors.toList());
        while (true) {
            String cccd = sc.nextLine();
            if (!listCCCD.contains(cccd)) {
                System.out.println("Khach hang nay ko ton tai trong he thong");
                System.out.print("Nhap lai:");
            } else {
                System.out.print("Nhap ma STK gom 6 chu so:");
                while (true) {
                    String stk = sc.nextLine();
                    if (!checkSTK(stk)) {
                        System.out.println("So tai khoan khong hop le");
                    } else {
                        Customer customer = bank.getCustomers().stream().filter(c -> c.getCustomerId().equals(cccd)).findFirst().get();
                        Account account = new Account();
                        account.setAccountNumber(stk);
                        System.out.print("Nhap so du: ");
                        while (true) {
                            double soDu = sc.nextDouble();
                            if (soDu < 50000) {
                                System.out.println("Nhap so du >50000");
                            } else {
                                account.setBalance(soDu);
                                break;
                            }
                        }
                        customer.getAccounts().add(account);
                        System.out.println("Them tai khoan thanh cong");
                        return;
                    }
                }
            }
        }
    }

    public static void hienThiDanhSachKhachHang() {
        System.out.println("Danh sach khach hang: ");
        for (Customer customer : bank.getCustomers()) {
            customer.displayInformation();
        }
    }

    public static void timKiemKhachHang() {
        System.out.println("Nhap CCCD khach hang: ");
        String cccd = sc.nextLine();
        Customer customer = bank.getCustomers().stream().filter(c -> c.getCustomerId().equals(cccd)).findFirst().get();
        if (!Objects.isNull(customer)) {
            customer.displayInformation();
        } else
            System.out.println("Khong co thong tin");

    }

    public static void timKiemKhachHangTheoTen() {
        System.out.println("Nhap ten khach hang: ");
        String ten = sc.nextLine();
        List<Customer> customers = bank.getCustomers().stream().filter(c -> c.getName().equals(ten)).collect(Collectors.toList());
        if (customers.size()!=0) {
            for (Customer customer : customers) {
                customer.toString();
            }
        } else
            System.out.println("Khong co thong tin");

    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("+----------+-------------------------+---------+");
            System.out.println("| NGAN HANG SO | FX21678@v2.0.0                |");
            System.out.println("+----------+-------------------------+---------+");
            System.out.printf("|%-46s|\n", "1. Them khach hang");
            System.out.printf("|%-46s|\n", "2. Them tai khoan khach hang");
            System.out.printf("|%-46s|\n", "3. Hien thi danh sach khach hang");
            System.out.printf("|%-46s|\n", "4. Tim theo CCCD");
            System.out.printf("|%-46s|\n", "5. Tim theo ten khach hang");
            System.out.printf("|%-46s|\n", "0. Thoát");
            System.out.println("+----------+-------------------------+---------+");
            System.out.println("Chuc nang: ");
            String chon = sc.nextLine();
            switch (chon) {
                case "0":
                    System.out.println("thoat");
                    return;
                case "1":
                    themKhachHang();
                    break;
                case "2":
                    themTaiKhoanKhachHang();
                    break;
                case "3":
                    hienThiDanhSachKhachHang();
                    break;

                case "4":
                    timKiemKhachHang();
                    break;

                case "5":
                    timKiemKhachHangTheoTen();
                    break;

                default:
                    System.out.println("Vui long nhap lai: ");

            }

        }
    }
}
