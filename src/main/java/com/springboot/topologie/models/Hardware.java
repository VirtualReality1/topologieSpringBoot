package com.springboot.topologie.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;

@Entity
public class Hardware {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 3, message = "IP must not be empty")
    private String ip;

    @ManyToMany (mappedBy = "hardwares")
    private List<Software> softwares;

    public Hardware(String ip, String description) {
    this.name = name;
    this.ip = ip;
    }

    public Hardware(){}


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Software> getSoftware() {
        return softwares;
    }

}