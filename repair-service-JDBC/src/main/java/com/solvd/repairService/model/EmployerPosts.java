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

    public String description() {
        return description;
    }

    public void description(String description) {
        this.description = description;
    }

    public String role() {
        return role;
    }
}
