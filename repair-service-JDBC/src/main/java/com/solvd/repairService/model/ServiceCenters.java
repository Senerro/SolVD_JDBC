package com.solvd.repairService.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

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

    @JsonGetter
    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    @JsonGetter
    public String address() {
        return address;
    }

    public void address(String address) {
        this.address = address;
    }

    @JsonGetter
    public byte[] photo() {
        return photo;
    }

    public void photo(byte[] photo) {
        this.photo = photo;
    }


    public void description(String description) {
        this.description = description;
    }

    @JsonGetter
    public String description() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + id() + "] " + "name: " + name + ", location: " + address + "\n";
    }
}
