package com.solvd.repairService.model;

public class EmployeePosts extends AbstractModel {
    private String role;
    private String description;

    public EmployeePosts(Long id) {
        super(id, EmployeePosts.class);
    }

    public EmployeePosts(Long id, String role, String description) {
        this(id);
        this.role = role;
        this.description = description;
    }

    public EmployeePosts(String role, String description) {
        this(0L);
        this.role = role;
        this.description = description;
    }

    public EmployeePosts() {
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
        if (this.role.equals(((EmployeePosts) object).role))
            if (this.description.equals(((EmployeePosts) object).description))
                return true;
        return false;
    }

    @Override
    public String toString() {
        return "["+id()+"]"+" post: " + role;
    }
}
