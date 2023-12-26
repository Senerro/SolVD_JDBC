package com.solvd.repairService.model;

public class CustomerProfiles extends AbstractModel
{
    private String nick;
    private String phone;
    private Users user;

    public CustomerProfiles(Long id) {
        super(id, CustomerProfiles.class);
        nick = "User#"+id;
        phone = "None";
    }
    public CustomerProfiles(Long id, String nick, String phone) {
        this(id);
        this.nick = nick;
        this.phone = phone;
    }
    public CustomerProfiles(CustomerProfiles profiles) {
        this(profiles.id());
        this.nick = profiles.nick;
        this.phone = profiles.phone;
        this.user = profiles.user;
    }

    public String nick() {
        return nick;
    }

    public void nick(String nick) {
         this.nick = nick;
    }

    public String phone() {
        return phone;
    }

    public void phone(String phone) {
       this.phone = phone;
    }

    public Users user() {
        return user;
    }

    public void user(Users user) {
        this.user = user;
    }
    @Override
    public boolean equals(Object object)
    {
        if(this.nick.equals(((CustomerProfiles) object).nick()))
            if (this.phone.equals(((CustomerProfiles) object).phone))
                return true;
        return false;
    }
}
