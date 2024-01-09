package com.solvd.repairService.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name ="ServiceCenter")
public class ServiceCenters extends AbstractModel {
    @XmlElement(name = "address")
    private String address;
    @XmlElement(name = "name")
    private String name;
    private byte[] photo;
    private String description;

    public ServiceCenters(Long id) {
        super(id, ServiceCenters.class);
    }

    public ServiceCenters(Long id, String address, String name, byte[] photo, String description) {
        this(id);
        this.address = address;
        this.name = name;
        this.photo = photo;
        this.description = description;
    }

    public ServiceCenters() {
        this(0L);
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String address() {
        return address;
    }

    public void address(String address) {
        this.address = address;
    }

    public byte[] photo() {
        return photo;
    }

    public void photo(byte[] photo) {
        this.photo = photo;
    }


    public void description(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
    @Override
    public String toString()
    {
        return "["+id()+"] "+ "name: " + name + ", location: " + address;
    }
}
