package app;

public abstract class Account {
    private String uid;
    private long balance;

    Account(String uid) {
        this.setUID(uid);
    }

    Account(String uid, long balance) {
        this(uid);
        this.setBalance(balance);
    }

    public String getUID() {
        return uid;
    }

    public long getBalance() {
        return balance;
    }

    protected void setUID(String uid) {
        this.uid = uid;
    }

    protected void setBalance(long value) {
        this.balance = value;
    }

    public abstract void details();
}