package app;

import java.time.LocalDate;

public class SavingAccount extends Account {
    private String aid;
    private float interestRate = 7;
    private int period;
    private LocalDate date;
    private String state;

    public SavingAccount(String aid, String uid, long balance, float interestRate, int period, LocalDate date) {
        super(uid, balance);
        this.setAID(aid);
        this.setInterestRate(interestRate);
        this.setPeriod(period);
        this.setDate(date);
        this.setState("active");
    }

    public String getAID() {
        return aid;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public int getPeriod() {
        return period;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    private void setAID(String aid) {
        this.aid = aid;
    }

    private void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    private void setPeriod(int period) {
        this.period = period;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    protected void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s\n", this.getAID(), this.getUID(), this.getBalance(),
                this.getInterestRate(), this.getPeriod(), this.getDate());
    }

    public static void title() {
        System.out.printf("%-12s %-10s %-15s %-12s %-10s %s\n", "ID", "UID", "Số tiền", "Ngày gửi", "Lãi suất",
                "Kỳ hạn");
    }

    @Override
    public void details() {
        System.out.printf("%-12s %-10s %-15d %-12s %-10.2f %d\n", this.getAID(), this.getUID(), this.getBalance(),
                this.getDate(), this.getInterestRate(), this.getPeriod());
    }

    public Double getInterest() {
        if (this.getDate().plusMonths(this.getPeriod()).isBefore(LocalDate.now())) {
            Double interest = this.getBalance() * this.getPeriod() * (double) this.getInterestRate() / 100;
            this.setDate(LocalDate.now());
            return interest;
        } else {
            System.out.println("Chưa đến hạn lĩnh xiền");
            return null;
        }
    }

    public Long finalSettlement() {
        if (this.getDate().plusMonths(this.getPeriod()).isBefore(LocalDate.now())) {
            Double interest = this.getBalance() * this.getPeriod() * (double) this.getInterestRate() / 100;
            Long value = this.getBalance() + interest.longValue();
            return value;
        } else {
            return this.getBalance();
        }
    }
}