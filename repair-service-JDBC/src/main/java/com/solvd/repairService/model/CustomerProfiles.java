package com.solvd.repairService.model;

public class CustomerProfiles extends AbstractModel
{
    private String nick;
    private String phone;
    private Users user;

    public CustomerProfiles(Long id) {
        super(id, CustomerProfiles.class);
    }
    public CustomerProfiles(Long id, String nick, String phone)
    {
        this(id);
        this.nick = nick;
        this.phone = phone;
    }

    public String nick() {
        return nick;
    }

    public String phone() {
        return phone;
    }
}
