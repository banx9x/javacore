import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Locale locale;

        while (true) {
            System.out.printf("Choose your language:\n1. Vietnamese\n2. English\n3. Chinese\n> ");

            int i;
            try {
                i = Integer.parseInt(in.nextLine());
                locale = i == 1 ? new Locale("vi", "VN") : i == 2 ? Locale.US : Locale.CHINA;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid");
            }
        }

        ResourceBundle r = ResourceBundle.getBundle("resources.text", locale);

        System.out.println("Hello in " + r.getString("language") + " is " + r.getString("greeting"));
        System.out.println("Goodbye in " + r.getString("language") + " is " + r.getString("goodbye"));

        in.close();
    }
}
