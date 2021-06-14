package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;

import javax.validation.constraints.NotNull;

public class AddSoftwareItemForm {
    @NotNull
    private int softwareId;

    @NotNull
    private int hardwareId;

    private Iterable<Hardware> hardwares;
    private Software software;

    public AddSoftwareItemForm(){ }

    public AddSoftwareItemForm(Iterable <Hardware> hardwares, Software software) {
        this.hardwares = hardwares;
        this.software = software;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }
    public int getHardwareId() {
        return hardwareId;
    }
    public void setHardwareId(int hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Iterable<Hardware> getHardwares(){
        return hardwares;
    }

    public Software getSoftware(){
        return software;
    }

}
