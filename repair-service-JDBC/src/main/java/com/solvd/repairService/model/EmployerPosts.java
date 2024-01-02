package com.solvd.repairService.model;

public class EmployerPosts extends AbstractModel {
    private String role;
    private String description;

    public EmployerPosts(Long id) {
        super(id, EmployerPosts.class);
    }

    public EmployerPosts(Long id, String role, String description) {
        this(id);
        this.role = role;
        this.description = description;
    }

    public EmployerPosts(String role, String description) {
        this(0L);
        this.role = role;
        this.description = description;
    }

    public EmployerPosts() {
        this(0L);
    }

    public String description() {
        return description;
    }

    public void description(String description) {
        this.description = description;
    }

    public String role() {
        return role;
    }

    public void role(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return id().hashCode() + role.hashCode() + 47;
    }
    @Override
    public boolean equals(Object object) {
        if(this.role.equals(((EmployerPosts) object).role))
            if (this.description.equals(((EmployerPosts) object).description))
                return true;
        return false;
    }
}
