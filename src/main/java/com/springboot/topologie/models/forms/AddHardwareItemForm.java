package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;

import javax.validation.constraints.NotNull;

public class AddHardwareItemForm {
    @NotNull
    private Long hardwareId;

    @NotNull
    private Long softwareId;

    private Iterable<Software> softwares;
    private Hardware hardware;

    public AddHardwareItemForm(){ }

    public AddHardwareItemForm(Iterable <Software> softwares, Hardware hardware) {
        this.softwares = softwares;
        this.hardware = hardware;
    }

    public Long getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Long hardwareId) {
        this.hardwareId = hardwareId;
    }
    public Long getSoftwareId() {
        return softwareId;
    }
    public void setSoftwareId(Long softwareId) {
        this.softwareId = softwareId;
    }

    public Iterable<Software> getSoftwares(){
        return softwares;
    }

    public Hardware getHardware(){
        return hardware;
    }

}