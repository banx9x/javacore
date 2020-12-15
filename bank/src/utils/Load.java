package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import app.Customer;
import app.PaymentAccount;
import app.SavingAccount;
import app.Staff;

public class Load extends Thread {
    ArrayList<PaymentAccount> paymentAccounts;
    ArrayList<SavingAccount> savingAccounts;
    ArrayList<Customer> customers;
    ArrayList<Staff> staffs;

    public Load(ArrayList<PaymentAccount> paymentAccounts, ArrayList<SavingAccount> savingAccounts, ArrayList<Customer> customers, ArrayList<Staff> staffs) {
        this.paymentAccounts = paymentAccounts;
        this.savingAccounts = savingAccounts;
        this.customers = customers;
        this.staffs = staffs;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                FileReader file;
                BufferedReader reader;
                String line;
                String dir = System.getProperty("user.dir");
                File paymentAccounts = new File(dir + "/paymentAccounts.csv");
                File savingAccounts = new File(dir + "/savingAccounts.csv");
                File customers = new File(dir + "/customers.csv");
                File staff = new File(dir + "/staffs.csv");

                if (!customers.exists()) {
                    customers.createNewFile();
                } else {
                    file = new FileReader(customers);
                    reader = new BufferedReader(file);

                    while ((line = reader.readLine()) != null) {
                        String[] l = line.split(",");
                        this.customers.add(new Customer(l[0], l[1], l[2], l[3]));
                    }
                }

                if (!staff.exists()) {
                    staff.createNewFile();
                } else {
                    file = new FileReader(staff);
                    reader = new BufferedReader(file);

                    while ((line = reader.readLine()) != null) {
                        String[] l = line.split(",");
                        this.staffs.add(new Staff(l[0], l[1], l[2], l[3]));
                    }
                }

                if (!paymentAccounts.exists()) {
                    paymentAccounts.createNewFile();
                } else {
                    file = new FileReader(paymentAccounts);
                    reader = new BufferedReader(file);

                    while ((line = reader.readLine()) != null) {
                        String[] l = line.split(",");
                        PaymentAccount p = new PaymentAccount(l[0], Long.parseLong(l[1]));

                        this.paymentAccounts.add(p);
                        this.customers.forEach(u -> {
                            if (u.getUID().equals(p.getUID())) {
                                u.setPaymentAccount(p);
                            }
                        });
                    }
                }

                if (!savingAccounts.exists()) {
                    savingAccounts.createNewFile();
                } else {
                    file = new FileReader(savingAccounts);
                    reader = new BufferedReader(file);

                    while ((line = reader.readLine()) != null) {
                        String[] l = line.split(",");
                        SavingAccount s = new SavingAccount(l[0], l[1], Long.parseLong(l[2]), Float.parseFloat(l[3]),
                                Integer.parseInt(l[4]), LocalDate.parse(l[5]));

                        this.savingAccounts.add(s);
                        this.customers.forEach(c -> {
                            if (c.getUID().equals(s.getUID())) {
                                c.setSavingAccounts(s);
                            }
                        });
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (SecurityException e) {
                System.out.println("Cannot create new file");
                return;
            }
            notify();
        }
    }
}

