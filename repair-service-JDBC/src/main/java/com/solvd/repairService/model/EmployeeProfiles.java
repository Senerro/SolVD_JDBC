package com.solvd.repairService.model;

public class EmployeeProfiles extends AbstractModel {
    private String fullName;
    private String phone;
    private Long postId;
    private Long serviceCenterId;
    private double experience;
    private Users user;
    private EmployeePosts post;
    private ServiceCenters center;

    public EmployeeProfiles(Long id) {
        super(id, EmployeeProfiles.class);
    }
    public EmployeeProfiles()
        {
            this(0L);
        }

    public EmployeeProfiles(Long id, String fullName, String phone, Long postId, double experience, Long serviceCenterId) {
        this(id);
        this.fullName = fullName;
        this.phone = phone;
        this.postId = postId;
        this.experience = experience;
        this.serviceCenterId = serviceCenterId;
    }

    public void post(EmployeePosts post) {
        this.post = post;
    }

    public double experience() {
        return experience;
    }

    public ServiceCenters serviceCenters() {
        return center;
    }

    public void serviceCenters(ServiceCenters center) {
        this.center = center;
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
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        if(id() > 0)
            builder.append("[").append(id()).append("] ");
        builder.append("Name: ").append(fullName).append(" ,phone ").append(phone).append("\n");
        builder.append("Exp ").append(experience).append(" years");

        if(post != null)
            builder.append("position: ").append(post.role());
        if(serviceCenters() != null)
            builder.append(", location: ").append(center.name() + " " + center.address());

        return builder.toString();
    }

}
