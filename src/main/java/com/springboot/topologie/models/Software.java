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

    @NotNull
    @Size(min = 3, max = 15)
    private String version;

    @NotNull
    @Size(min = 3, max = 15)
    private String type;

    @NotNull
    @Size(min = 3, max = 15)
    private String OsName;

    @NotNull
    @Size(min = 3, max = 15)
    private String OsVersion;

    @OneToMany(mappedBy = "software")
    private List<Communication> communication;

    @ManyToMany
    private List<Hardware> hardwares;

    public Software()  {

    }

    private String getSoftwareName() {
        return name;
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


    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOsName() {
        return OsName;
    }

    public void setOsName(String osName) {
        OsName = osName;
    }

    public String getOsVersion() {
        return OsVersion;
    }

    public void setOsVersion(String osVersion) {
        OsVersion = osVersion;
    }

    public List<Communication> getCommunication() {
        return communication;
    }

    public void setCommunication(List<Communication> communication) {
        this.communication = communication;
    }

    public void setHardwares(List<Hardware> hardwares) {
        this.hardwares = hardwares;
    }
}
