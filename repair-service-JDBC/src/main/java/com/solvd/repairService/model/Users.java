package com.solvd.repairService.model;
public class Users extends AbstractModel {
    private String login;
    private String password;
    private boolean role;

    public Users()
    {
        super(0L, Users.class);
    }

    public Users(Long id) {
        super(id, Users.class);
    }
    public Users(String login, String password) {
        this();
        this.login = login;
        this.password = password;
        role = false;
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

    public String password() {
        return password;
    }
    public boolean role()
    {
        return role;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.role)
            stringBuilder.append("Employee with login [");
        else
            stringBuilder.append("Customer with login [");

        stringBuilder.append(this.login);
        stringBuilder.append("] And password [");
        stringBuilder.append(this.password).append("] ");

     return stringBuilder.toString();
    }
}
