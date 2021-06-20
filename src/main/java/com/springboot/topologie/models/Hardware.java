package com.springboot.topologie.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;

@Entity
public class Hardware {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 3, max = 15)
    private String version;

    @NotNull
    @Size(min = 3, message = "IP must not be empty")
    private String ip;

    @ManyToMany (mappedBy = "hardwares")
    private List<Software> softwares;

    public Hardware(String ip, String name) {
    this.name = name;
    this.ip = ip;
    }

    public Hardware(){}


    public void addItem(Software item) {
        if(!softwares.contains(item)){
            softwares.add(item);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIp() {
        return "IP: " + ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }
}