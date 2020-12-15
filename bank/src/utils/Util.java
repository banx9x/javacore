package utils;

public class Util {
    public static String capitalize(String name) {
        String[] temp = name.split(" ");

        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1);
        }

        name = String.join(" ", temp);
        return name;
    }
}
