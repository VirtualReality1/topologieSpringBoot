package com.springboot.topologie.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Segment {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToMany
    private List<Field> fields;

    public Segment(String name){
        this.name = name;
    }

    public Segment(){}

    public void addItem(Field item){
         if(!fields.contains(item)){
             fields.add(item);
         }
    }

    public int getId(){return id;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public List<Field> getFields(){return fields;}
}
