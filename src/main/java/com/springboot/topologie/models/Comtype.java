package com.springboot.topologie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Comtype {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;



    @OneToMany(mappedBy = "comtype")
    private List<Communication> communications;

    @OneToMany(mappedBy = "comtype")
    private List<Messagetype> messagetype;

    public Comtype(){}
    public Comtype(String name) {
        this.name = name;
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

    public List<Communication> getCommunications() {
        return communications;
    }

    public void addItem(Communication item) {
        if(!communications.contains(item)) {
            communications.add(item);
        }
    }
    @Override
    public String toString(){
        return this.name;
    }
}