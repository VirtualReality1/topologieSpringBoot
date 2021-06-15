package com.springboot.topologie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="tr_trigger")
public class TrTrigger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @OneToOne(mappedBy = "trigger", optional = true)
    private Communication communication;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrTrigger(){}
}
