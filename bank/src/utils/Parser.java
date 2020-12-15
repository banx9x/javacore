package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Pattern pattern;
    Matcher matcher;

    private String parse(Regex reg, String s) {
        pattern = Pattern.compile(reg.get());
        matcher = pattern.matcher(s);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    public String command(String s) {
        return parse(Regex.COMMAND, s);
    }

    public String uid(String s) {
        return parse(Regex.UID, s);
    }

    public String aid(String s) {
        return parse(Regex.AID, s);
    }

    public String name(String s) {
        return parse(Regex.NAME, s);
    }

    public String username(String s) {
        return parse(Regex.USERNAME, s);
    }

    public String password(String s) {
        return parse(Regex.PASSWORD, s);
    }

    public String newpass(String s) {
        return parse(Regex.NEW, s);
    }

    public String value(String s) {
        return parse(Regex.VALUE, s);
    }

    public String period(String s) {
        return parse(Regex.PERIOD, s);
    }

    public String show(String s) {
        if (parse(Regex.PAYMENT, s) != null) {
            return parse(Regex.PAYMENT, s);
        } else {
            return parse(Regex.ACCOUNT, s);
        }
    }
}

