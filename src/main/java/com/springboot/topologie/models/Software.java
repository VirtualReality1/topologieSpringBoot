package com.springboot.topologie.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;


@Entity
public class Software {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @ManyToMany
    private List<Hardware> hardwares;

    public Software() {
    }

    public void addItem(Hardware item) {
        if(!hardwares.contains(item)){
            hardwares.add(item);
        }
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hardware> getHardwares() {
        return hardwares;
    }


}
