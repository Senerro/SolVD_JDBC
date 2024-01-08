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
    public CustomerProfiles()
    {
        this(0L);
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
        this.id(user.id());
    }
    @Override
    public boolean equals(Object object)
    {
        if(this.nick.equals(((CustomerProfiles) object).nick()))
            if (this.phone.equals(((CustomerProfiles) object).phone))
                return true;
        return false;
    }

    @Override
    public int hashCode() {
        return nick.hashCode() + phone.hashCode() + id().hashCode() + 29;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        return builder.append("[").append(id()).append("]")
                .append(" - ").append(nick).append("\n")
                .append(phone()).toString();
    }
}
