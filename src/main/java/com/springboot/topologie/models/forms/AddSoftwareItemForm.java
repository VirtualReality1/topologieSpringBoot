package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;

import javax.validation.constraints.NotNull;

public class AddSoftwareItemForm {
    @NotNull
    private Long softwareId;

    @NotNull
    private Long hardwareId;

    private Iterable<Hardware> hardwares;
    private Software software;

    public AddSoftwareItemForm(){ }

    public AddSoftwareItemForm(Iterable <Hardware> hardwares, Software software) {
        this.hardwares = hardwares;
        this.software = software;
    }

    public Long getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(Long softwareId) {
        this.softwareId = softwareId;
    }
    public Long getHardwareId() {
        return hardwareId;
    }
    public void setHardwareId(Long hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Iterable<Hardware> getHardwares(){
        return hardwares;
    }

    public Software getSoftware(){
        return software;
    }

}
