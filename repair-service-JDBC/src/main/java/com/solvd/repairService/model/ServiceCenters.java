package com.solvd.repairService.model;

public class ServiceCenters extends AbstractModel{
    private Long id;
    private String address;
    private String name;
    private byte[] photo;
    private String description;

    public ServiceCenters(Long id) {
        super(id, ServiceCenters.class);
    }
    public ServiceCenters(Long id,  String address, String name, byte[] photo, String description)
    {
        this(id);
        this.address = address;
        this.name = name;
        this.photo = photo;
        this.description = description;
    }

    public String description() {
        return description;
    }

    public byte[] photo() {
        return photo;
    }

    public String address() {
        return address;
    }

    public String nme() {
        return name;
    }

}
