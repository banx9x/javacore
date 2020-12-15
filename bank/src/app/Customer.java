package app;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends User {
    private PaymentAccount paymentAccount = null;
    private ArrayList<SavingAccount> savingAccounts = new ArrayList<>();

    public Customer(String uid, String name, String username, String password) {
        super(uid, name, username, password, Role.CUSTOMER);
    }

    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    public ArrayList<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setPaymentAccount(PaymentAccount paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public void setSavingAccounts(SavingAccount savingAccounts) {
        this.savingAccounts.add(savingAccounts);
    }

    public void showPaymentAccount() {
        PaymentAccount.title();
        this.getPaymentAccount().details();
    }

    public void showSavingAccounts() {
        if (this.getSavingAccounts().size() > 0) {
            SavingAccount.title();
            this.getSavingAccounts().forEach(s -> s.details());
        } else {
            System.out.println("Chưa có tài khoản tiết kiệm nào!");
        }
    }

    public void withdraw(long value) {
        try {
            this.getPaymentAccount().withdraw(value);
            System.out.println("Rút tiền thành công!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getInterest(String aid) {
        SavingAccount s = null;

        for (SavingAccount a : savingAccounts) {
            if (a.getAID().equals(aid)) {
                s = a;
                break;
            }
        }

        if (s != null) {
            Double interest = s.getInterest();
            if (interest != null) {
                this.getPaymentAccount().deposit(interest.longValue());
                System.out.println("Lĩnh tiền lãi oke");
            }
        } else {
            System.out.println("Số tài khoản không đúng");
        }
    }

    public SavingAccount openSavingAccount(long value, float rate, int period) {
        // Lỗi khi tạo tài khoản mà không có tài khoản mặc định
        String aid = this.getUID() + (this.getSavingAccounts().size() + 1);

        try {
            this.getPaymentAccount().withdraw(value);
            SavingAccount s = new SavingAccount(aid, this.getUID(), value, rate, period, LocalDate.now());
            this.setSavingAccounts(s);
            System.out.println("Mở tài khoản tiết kiệm thành công!");
            return s;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void closeSavingAccount(String aid) {
        SavingAccount account = null;

        for (SavingAccount a : this.getSavingAccounts()) {
            if (a.getAID().equals(aid)) {
                account = a;
                break;
            }
        }

        if (account == null) {
            System.out.println("Số tài khoản không hợp lệ");
            return;
        }

        this.getPaymentAccount().deposit(account.finalSettlement());
        this.savingAccounts.remove(account);
        account.setState("close");
        System.out.println("Tất toán tài khoản tiết kiệm thành công");
    }
}

