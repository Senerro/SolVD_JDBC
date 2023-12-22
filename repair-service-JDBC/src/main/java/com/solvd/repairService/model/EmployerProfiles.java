package com.solvd.repairService.model;

public class EmployerProfiles extends AbstractModel{
    private String fullName;
    private String phone;
    private Long postId;
    private double experience;
    private Users user;
    private EmployerPosts post;
    public EmployerProfiles(Long id) {
        super(id, EmployerProfiles.class);
    }

    public EmployerProfiles(Long id, String fullName, String phone, Long postId, double experience) {
        this(id);
        this.fullName = fullName;
        this.phone = phone;
        this.postId = postId;
        this.experience = experience;
    }

    public double experience() {
        return experience;
    }

    public Long postId() {
        return postId;
    }

    public String fullName() {
        return fullName;
    }

    public String phone() {
        return phone;
    }
}
