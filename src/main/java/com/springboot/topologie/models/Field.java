package com.springboot.topologie.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.List;

@Entity
public class Field {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;


    @ManyToMany (mappedBy = "fields")
    private List<Segment> segments;

    public Field(String name) {
        this.name = name;
    }

    public Field(){}


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Segment> getSegments() {
        return segments;
    }

}