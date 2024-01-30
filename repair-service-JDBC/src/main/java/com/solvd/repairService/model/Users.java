package com.solvd.repairService.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "userType")
@XmlRootElement(name = "user" )
public class Users extends AbstractModel {
    @XmlElement
    private String login;
    @XmlElement
    private String password;
    @XmlElement
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

    @JsonGetter
    public String login() {
        return login;
    }

    public void login(String login) {
        this.login = login;
    }

    @JsonGetter
    public String password() {
        return password;
    }

    public void password(String password) {
        this.password = password;
    }

    @JsonGetter
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
