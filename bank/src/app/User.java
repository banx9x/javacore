package app;

public abstract class User {
    private String uid;
    private String name;
    private String username;
    private String password;
    private Role role;

    User(String uid, String name, String username, String password, Role role) {
        this.setUID(uid);
        this.setName(name);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
    }

    public String getUID() {
        return this.uid;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    private void setUID(String uid) {
        this.uid = uid;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setRole(Role role) {
        this.role = role;
    }

    public void changePassword(String old, String newPass) throws IllegalArgumentException {
        if (this.getPassword().equals(old)) {
            this.setPassword(newPass);
        } else {
            throw new IllegalArgumentException("Mật khẩu cũ không đúng, hoặc mật khẩu mới không trùng khớp");
        }
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s\n", this.getUID(), this.getName(), this.getUsername(), this.getPassword());
    }

    public void details() {
        System.out.printf("%s %s %s\n", this.getUID(), this.getName(), this.getUsername());
    }
}
