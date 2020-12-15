package app;

public class PaymentAccount extends Account {
    PaymentAccount(String uid) {
        super(uid);
    }

    public PaymentAccount(String uid, long balance) {
        super(uid, balance);
    }

    public void withdraw(long value) throws IllegalArgumentException {
        if (this.getBalance() < value) {
            throw new IllegalArgumentException("Số dư không đủ");
        } else {
            this.setBalance(this.getBalance() - value);
        }
    }

    public void deposit(long value) {
        this.setBalance((long) (this.getBalance() + value));
    }

    @Override
    public String toString() {
        return String.format("%s,%d\n", this.getUID(), this.getBalance());
    }

    public static void title() {
        System.out.printf("%-10s %s\n", "UID", "Số dư");
    }

    @Override
    public void details() {
        System.out.printf("%-10s %d\n", this.getUID(), this.getBalance());
    }
}