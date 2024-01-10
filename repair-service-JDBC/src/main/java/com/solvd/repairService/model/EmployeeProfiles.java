package com.solvd.repairService.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType (name = "EmployeeProfileType")
@XmlRootElement (name = "EmployeeProfile")
public class EmployeeProfiles extends AbstractModel {
    @XmlElement(name = "fullName")
    private String fullName;
    @XmlElement(name = "phone")
    private String phone;
    @XmlElement(name = "postId")
    private Long postId;
    @XmlElement(name = "serviceCenterId")
    private Long serviceCenterId;
    @XmlElement(name = "experience")
    private double experience;
    @XmlElement(name = "user")
    private Users user;
    @XmlElement(name = "post")
    private EmployeePosts post;

    private ServiceCenters center;

    @JsonGetter
    public ServiceCenters center() {
        return center;
    }

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
    public EmployeeProfiles(Long id, String fullName, String phone, double experience, Long serviceCenterId) {
        this(id);
        this.fullName = fullName;
        this.phone = phone;
        this.postId = postId;
        this.experience = experience;
        this.serviceCenterId = serviceCenterId;
    }

    public EmployeeProfiles(String fullname, String phone, double experience, Long serviceCenterId) {
        this(0L, fullname, phone, experience, serviceCenterId);
    }
    public EmployeeProfiles(String fullname, String phone, double experience, Users user, EmployeePosts post, ServiceCenters center) {
        this(user.id(), fullname, phone, experience, center.id());
        this.post = post;
        this.center = center;
        this.user = user;
    }

    @JsonGetter
    public Users user() {
        return user;
    }

    public void post(EmployeePosts post) {
        this.post = post;
        postId = post.id();
    }

    @JsonGetter
    public EmployeePosts post() {
        return post;
    }

    @JsonGetter
    public double experience() {
        return experience;
    }

    @JsonGetter
    public ServiceCenters serviceCenters() {
        return center;
    }

    public void serviceCenters(ServiceCenters center) {
        this.center = center;
        this.serviceCenterId = center.id();
    }

    public void experience(double experience) {
        this.experience = experience;
    }

    @JsonGetter
    public Long postId() {
        return postId;
    }

    public void postId(Long postId) {
        this.postId = postId;
    }

    @JsonGetter
    public String fullName() {
        return fullName;
    }

    public void fullName(String fullName) {
        this.fullName = fullName;
    }


    public void phone(String phone) {
        this.phone = phone;
    }

    @JsonGetter
    public String phone() {
        return phone;
    }

    @JsonGetter
    public Long serviceCenterId() {
        return serviceCenterId;
    }

    public void user(Users user) {
        this.user = user;
        this.id(user.id());
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
