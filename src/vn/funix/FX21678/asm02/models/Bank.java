package vn.funix.FX21678.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {

    private final String id;
    private final List<Customer> customers;

    public String getId() {
        return id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer newCustomer) {
        if (customers.contains(newCustomer.getCustomerId()))
            customers.add(newCustomer);
        else
            System.out.println("CCCD này đã dc su dung");
    }

    public boolean isCustomerExisted(String customerId) {
        if (customers.contains(customerId))
            return true;
        return false;
    }




}
