package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.Customer;
import app.PaymentAccount;
import app.SavingAccount;
import app.Staff;

public class Save extends Thread {
    ArrayList<PaymentAccount> paymentAccounts;
    ArrayList<SavingAccount> savingAccounts;
    ArrayList<Customer> customers;
    ArrayList<Staff> staffs;

    public Save(ArrayList<PaymentAccount> paymentAccounts, ArrayList<SavingAccount> savingAccounts,
            ArrayList<Customer> customers, ArrayList<Staff> staffs) {
        this.paymentAccounts = paymentAccounts;
        this.savingAccounts = savingAccounts;
        this.customers = customers;
        this.staffs = staffs;
    }

    public void run() {
        String dir = System.getProperty("user.dir");

        try (FileWriter file = new FileWriter(dir + "/paymentAccounts.csv");
                BufferedWriter writer = new BufferedWriter(file)) {
            paymentAccounts.forEach(p -> {
                try {
                    writer.write(p.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter(dir + "/savingAccounts.csv");
                BufferedWriter writer = new BufferedWriter(file)) {
            savingAccounts.forEach(s -> {
                try {
                    if ("active".equals(s.getState())) {
                        writer.write(s.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter(dir + "/customers.csv");
                BufferedWriter writer = new BufferedWriter(file)) {
            customers.forEach(s -> {
                try {
                    writer.write(s.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter(dir + "/staffs.csv"); BufferedWriter writer = new BufferedWriter(file)) {
            staffs.forEach(s -> {
                try {
                    writer.write(s.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
