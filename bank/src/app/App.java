package app;

import java.util.Scanner;
import utils.Help;
import utils.Parser;
import utils.Util;

public class App {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Staff staff = null;
        Customer customer = null;
        Help help = new Help();
        Parser parser = new Parser();
        Scanner in = new Scanner(System.in);
        String s;
        String prompt = "$ ";

        System.out.println("Ngân hàng nhà nước Việt Nam");
        System.out.println("Gõ 'help' để xem hướng dẫn thao cmn tác");
        System.out.println("Gõ 'quit' để thoát");

        while (true) {
            System.out.print(prompt);
            s = in.nextLine().trim().toLowerCase().replaceAll("\\s+", " ");

            if ("".equals(s)) {
                continue;
            }

            if ("quit".equals(s)) {
                break;
            }

            if ("help".equals(s)) {
                help.print(staff == null ? customer : staff);
                continue;
            }

            String command = parser.command(s);

            if (command == null) {
                continue;
            }

            String uid = parser.uid(s);
            String aid = parser.aid(s);
            String name = parser.name(s);
            String username = parser.username(s);
            String password = parser.password(s);
            String value = parser.value(s);
            String period = parser.period(s);
            String show = parser.show(s);
            String newpass = parser.newpass(s);

            switch (command) {
                case "signin":
                    if (staff != null || customer != null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    System.out.printf(username == null ? "'-u' không hợp lệ\n" : "");
                    System.out.printf(password == null ? "'-p' không hợp lệ\n" : "");

                    if (username != null && password != null) {
                        User user = bank.signin(username, password);
                        if (user != null && user.getRole() == Role.CUSTOMER) {
                            customer = (Customer) user;
                        } else if (user != null && user.getRole() == Role.STAFF) {
                            staff = (Staff) user;
                        }
                    }

                    break;

                case "signout":
                    if (staff == null && customer == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    staff = null;
                    customer = null;
                    System.out.println("Đăng xuất thành công");
                    break;

                case "create":
                    if (staff == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    System.out.printf(uid == null ? "'-uid' không hợp lệ\n" : "");
                    System.out.printf(name == null ? "'-n' không hợp lệ\n" : "");
                    System.out.printf(username == null ? "'-u' không hợp lệ\n" : "");
                    System.out.printf(password == null ? "'-p' không hợp lệ\n" : "");

                    if (uid != null && name != null && username != null && password != null) {
                        if (bank.exists(uid, username)) {
                            System.out.println("UID hoặc username đã tồn tại");
                        } else {
                            name = Util.capitalize(name);
                            Customer c = staff.createAccount(uid, name, username, password);
                            bank.addCustomer(c);
                            bank.save();
                            System.out.println("Tạo tài khoản thành công");
                        }
                    }

                    break;

                case "show":
                    if (staff == null && customer == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    System.out.printf(show == null ? "Lệnh 'show' không hợp lệ\n" : "");

                    if (customer != null) {
                        if ("-s".equals(show)) {
                            customer.showSavingAccounts();
                        } else if ("-p".equals(show)) {
                            customer.showPaymentAccount();
                        }
                    }

                    if (staff != null) {
                        System.out.printf(uid == null ? "'-uid' không hợp lệ\n" : "");

                        Customer c = bank.getCustomer(uid);

                        if (c == null) {
                            System.out.println("UID không tồn tại");
                            break;
                        }

                        if ("-p".equals(show)) {
                            staff.showPaymentAccount(c);
                        } else if ("-s".equals(show)) {
                            staff.showSavingAccount(c);
                        } else {
                            System.out.println("Số tài khoản không hợp lệ");
                        }
                    }

                    break;

                case "deposit":
                    if (staff == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    System.out.printf(uid == null ? "'-uid' không hợp lệ\n" : "");
                    System.out.printf(value == null ? "'-v' không hợp lệ\n" : "");

                    if (uid != null && value != null) {
                        Customer c = bank.getCustomer(uid);

                        if (c == null) {
                            System.out.println("UID không tồn tại");
                            break;
                        }

                        staff.deposit(c, Long.parseLong(value));
                        bank.save();
                        System.out.println("Gửi tiền vào tài khoản thành công");
                    }

                    break;

                case "withdraw":
                    if (staff == null && customer == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    if (customer != null) {
                        System.out.printf(value == null ? "'-v' không hợp lệ\n" : "");
                        customer.withdraw(Long.parseLong(value));
                        bank.save();
                    } else if (staff != null) {
                        System.out.printf(uid == null ? "'-uid' không hợp lệ\n" : "");
                        System.out.printf(value == null ? "'-v' không hợp lệ\n" : "");

                        if (uid != null && value != null) {
                            Customer c = bank.getCustomer(uid);

                            if (c == null) {
                                System.out.println("UID không tồn tại");
                                break;
                            }

                            staff.withdraw(c, Long.parseLong(value));
                            bank.save();
                        }
                    }

                    break;

                case "saving":
                    if (staff == null && customer == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    if (customer != null) {
                        System.out.printf(value == null ? "'-v' không hợp lệ\n" : "");
                        System.out.printf(period == null ? "'-pr' không hợp lệ\n" : "");

                        if (value != null && period != null) {
                            SavingAccount sa = customer.openSavingAccount(Long.parseLong(value), bank.getInterestRate(),
                                    Integer.parseInt(period));

                            if (sa != null) {
                                bank.addSavingAccount(sa);
                                bank.save();
                            }
                        }
                    } else if (staff != null) {
                        System.out.printf(uid == null ? "'-uid' không hợp lệ\n" : "");
                        System.out.printf(value == null ? "'-v' không hợp lệ\n" : "");
                        System.out.printf(period == null ? "'-pr' không hợp lệ\n" : "");

                        if (uid != null && value != null && period != null) {
                            Customer c = bank.getCustomer(uid);

                            if (c == null) {
                                System.out.println("UID không tồn tại");
                                break;
                            }

                            SavingAccount sa = staff.openSavingAccount(c, Long.parseLong(value), bank.getInterestRate(),
                                    Integer.parseInt(period));

                            if (sa != null) {
                                bank.addSavingAccount(sa);
                                bank.save();
                            }
                        }
                    }

                    break;

                case "getinterest":
                    if (staff == null && customer == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    if (customer != null) {
                        System.out.printf(aid == null ? "'-aid' không hợp lệ\n" : "");

                        if (aid != null) {
                            customer.getInterest(aid);
                            bank.save();
                        }
                    } else if (staff != null) {
                        System.out.printf(uid == null ? "'-uid' không hợp lệ\n" : "");
                        System.out.printf(aid == null ? "'-aid' không hợp lệ\n" : "");

                        if (uid != null && aid != null) {
                            Customer c = bank.getCustomer(uid);

                            if (c == null) {
                                System.out.println("UID không tồn tại");
                                break;
                            }

                            staff.getInterest(c, aid);
                            bank.save();
                        }
                    }

                    break;

                case "closesaving":
                    if (staff == null && customer == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    if (customer != null) {
                        System.out.printf(aid == null ? "'-aid' không hợp lệ\n" : "");

                        if (aid != null) {
                            customer.closeSavingAccount(aid);
                            bank.save();
                        }
                    } else {
                        System.out.printf(uid == null ? "'-uid' không hợp lệ\n" : "");
                        System.out.printf(aid == null ? "'-aid' không hợp lệ\n" : "");

                        if (uid != null && aid != null) {
                            Customer c = bank.getCustomer(uid);

                            if (c == null) {
                                System.out.println("UID không tồn tại");
                                break;
                            }

                            staff.closeSavingAccount(c, aid);
                            bank.save();
                        }
                    }

                    break;

                case "changepass":
                    if (staff == null && customer == null) {
                        System.out.println("Unauthorized");
                        break;
                    }

                    if (customer != null) {
                        System.out.printf(password == null ? "'-p' không hợp lệ\n" : "");
                        System.out.printf(newpass == null ? "'-new' không hợp lệ\n" : "");
                        if (password != null && newpass != null) {
                            customer.changePassword(password, newpass);
                            bank.save();
                            System.out.println("Thay đối mật khẩu thành công");
                        }
                    }

                    if (staff != null) {
                        System.out.printf(password == null ? "'-p' không hợp lệ\n" : "");
                        System.out.printf(newpass == null ? "'-new' không hợp lệ\n" : "");
                        if (password != null && newpass != null) {
                            staff.changePassword(password, newpass);
                            bank.save();
                            System.out.println("Thay đối mật khẩu thành công");
                        }
                    }

                    break;

                default:
                    System.out.println("Lệnh '" + command + "' không hợp lệ!");
            }
        }
        in.close();
    }
}
