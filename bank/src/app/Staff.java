package app;

public class Staff extends User {
    public Staff(String uid, String name, String username, String password) {
        super(uid, name, username, password, Role.STAFF);
    }

    public Customer createAccount(String uid, String name, String username, String password) {
        Customer c = new Customer(uid, name, username, password);
        PaymentAccount p = new PaymentAccount(uid);
        c.setPaymentAccount(p);
        return c;
    }

    public void showPaymentAccount(Customer c) {
        c.showPaymentAccount();
    }

    public void showSavingAccount(Customer c) {
        c.showSavingAccounts();
    }

    public void deposit(Customer c, long value) {
        c.getPaymentAccount().deposit(value);
    }

    public void withdraw(Customer c, long value) {
        c.withdraw(value);
    }

    public SavingAccount openSavingAccount(Customer c, long value, float rate, int period) {
        return c.openSavingAccount(value, rate, period);
    }

    public void getInterest(Customer c, String aid) {
        c.getInterest(aid);
    }

    public void closeSavingAccount(Customer c, String aid) {
        c.closeSavingAccount(aid);
    }
}
