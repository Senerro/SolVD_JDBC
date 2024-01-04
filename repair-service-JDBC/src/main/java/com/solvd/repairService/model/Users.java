package com.solvd.repairService.model;

public class Users extends AbstractModel {
    private String login;
    private String password;
    private boolean role;

    public Users() {
        super(0L, Users.class);
    }

    public Users(Long id) {
        super(id, Users.class);
    }
    public Users(Users user) {
        super(user.id(), Users.class);
        this.login = user.login();
        this.password = user.password();
        this.role = user.role;
    }

    public Users(String login, String password) {
        this();
        this.login = login;
        this.password = password;
        role = false;
    }
    public Users(String login, String password, boolean role) {
        this(login, password);
        this.role = role;
    }

    public Users(Long id, String login, String password, boolean role) {
        this(id);
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String login() {
        return login;
    }

    public void login(String login) {
        this.login = login;
    }

    public String password() {
        return password;
    }

    public void password(String password) {
        this.password = password;
    }

    public boolean role() {
        return role;
    }

    public void role(boolean b) {
       this.role = b;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.role)
            stringBuilder.append("Employee with login [");
        else
            stringBuilder.append("Customer with login [");

        stringBuilder.append(this.login);
        stringBuilder.append("] And password [");
        stringBuilder.append(this.password).append("] ");

        return stringBuilder.toString();
    }
}
