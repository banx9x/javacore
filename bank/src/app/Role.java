package app;

public enum Role {
    CUSTOMER(1), STAFF(2);

    private Integer role;

    Role(Integer role) {
        this.role = role;
    }

    public Integer get() {
        return this.role;
    }
}
