package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;

import javax.validation.constraints.NotNull;

public class AddHardwareItemForm {
    @NotNull
    private int hardwareId;

    @NotNull
    private int softwareId;

    private Iterable<Software> softwares;
    private Hardware hardware;

    public AddHardwareItemForm(){ }

    public AddHardwareItemForm(Iterable <Software> softwares, Hardware hardware) {
        this.softwares = softwares;
        this.hardware = hardware;
    }

    public int getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(int hardwareId) {
        this.hardwareId = hardwareId;
    }
    public int getSoftwareId() {
        return softwareId;
    }
    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public Iterable<Software> getSoftwares(){
        return softwares;
    }

    public Hardware getHardware(){
        return hardware;
    }

}