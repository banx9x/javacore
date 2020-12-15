package utils;

public enum Regex {
    COMMAND("^([a-z]+)[ ]*"), UID("-uid ([0-9]+)( -|$)"), AID("-aid ([0-9]+)( -|$)"), NAME("-n ([a-z ]+)( -|$)"),
    USERNAME("-u ([a-z0-9]+)( -|$)"), PASSWORD("-p ([a-z0-9]+)( -|$)"), VALUE("-v ([0-9]+)( -|$)"),
    PERIOD("-pr ([0-9]+)( -|$)"), PAYMENT(" (-p)( -|$)"), ACCOUNT(" (-s)( -|$)"), NEW("-new ([a-z0-9]+)( -|$)");

    private String reg;

    Regex(String reg) {
        this.reg = reg;
    }

    public String get() {
        return this.reg;
    }
}
