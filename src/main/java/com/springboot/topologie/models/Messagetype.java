package com.springboot.topologie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Messagetype {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @ManyToOne
    @JoinColumn(name="comtype_id", referencedColumnName = "id")
    private Comtype comtype;

    @OneToMany(mappedBy = "messagetype")
    private List<Segment> segment;

    @OneToMany(mappedBy = "messagetype")
    private List<Communication> communication;

    public Messagetype(){}
    public Messagetype(String name) {
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

    @Override
    public String toString(){
        return this.name;
    }

    public List<Segment> getSegment() {
        return segment;
    }

    public List<Communication> getCommunication() {
        return communication;
    }
}