package com.solvd.repairService.model;

public class EmployerProfiles extends AbstractModel {
    private String fullName;
    private String phone;
    private Long postId;
    private Long serviceCenterId;
    private double experience;
    private Users user;
    private EmployerPosts post;

    public EmployerProfiles(Long id) {
        super(id, EmployerProfiles.class);
    }

    public EmployerProfiles(Long id, String fullName, String phone, Long postId, double experience, Long serviceCenterId) {
        this(id);
        this.fullName = fullName;
        this.phone = phone;
        this.postId = postId;
        this.experience = experience;
        this.serviceCenterId = serviceCenterId;
    }

    public double experience() {
        return experience;
    }

    public void experience(double experience) {
        this.experience = experience;
    }

    public Long postId() {
        return postId;
    }

    public void postId(Long postId) {
        this.postId = postId;
    }

    public String fullName() {
        return fullName;
    }

    public void fullName(String fullName) {
        this.fullName = fullName;
    }

    public void phone(String phone) {
        this.phone = phone;
    }

    public String phone() {
        return phone;
    }

    public Long serviceCenterId() {
        return serviceCenterId;
    }

    public void serviceCenterId(Long serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

}
