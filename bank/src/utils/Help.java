package utils;

import app.Role;
import app.User;

public class Help {
    public void print(User user) {
        if (user == null) {
            System.out.println("Đăng nhập: \n\tsignin -u username -p password");

        } else if (user.getRole() == Role.CUSTOMER) {
            System.out.println("\nNhập 'signout' để đăng xuất");
            System.out.println("\nXem tài khoản thanh toán:\n\tshow -p");
            System.out.println("\nXem tài khoản tiết kiệm: \n\tshow -s");
            System.out.println("\nRút tiền từ tài khoản: \n\twithdraw -v value");
            System.out.println("\nMở tài khoản tiết kiệm: \n\tsaving -v value -pr period");
            System.out.println("\nLĩnh tiền lãi từ tài khoản tiết kiệm: \n\tgetinterest -aid aid");
            System.out.println("\nTất toán tài khoản tiết kiệm: \n\tclosesaving -aid aid");
            System.out.println("\nThay đổi mật khẩu tài khoản: \n\tchangepass -p password -new newpass");

        } else if (user.getRole() == Role.STAFF) {
            System.out.println("\nNhập 'signout' để đăng xuất");
            System.out.println("\nTạo tài khoản: \n\tcreate -uid uid -n name -u username -p password");
            System.out.println("\nXem tài khoản thanh toán: \n\tshow -p -uid uid");
            System.out.println("\nXem tài khoản tiết kiệm: \n\tshow -s -uid uid");
            System.out.println("\nGửi tiền vào tài khoản: \n\tdeposit -uid uid -v value");
            System.out.println("\nRút tiền từ tài khoản: \n\twithdraw -uid uid -v value");
            System.out.println("\nMở tài khoản tiết kiệm: \n\tsaving -uid id -v value -pr period");
            System.out.println("\nLĩnh tiền lãi từ tài khoản tiết kiệm: \n\tgetinterest -uid uid -aid aid");
            System.out.println("\nTất toán tài khoản tiết kiệm: \n\tclosesaving -uid uid -aid aid");
            System.out.println("\nThay đổi mật khẩu tài khoản: \n\tchangepass -p password -new newpass");
        }
    }
}
