package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.*;

import javax.validation.constraints.NotNull;

public class AddComTypeItemForm {
    @NotNull
    private Long comtypeId;

    @NotNull
    private Long communicationId;

    private Iterable<Communication> communications;
    private Comtype comtype;

    public AddComTypeItemForm(){ }

    public AddComTypeItemForm(Iterable <Communication> communications, Comtype comtype) {
        this.communications = communications;
        this.comtype = comtype;
    }

    public Long getComtypeId() {
        return comtypeId;
    }

    public void setComtypeId(Long comtypeId) {
        this.comtypeId = comtypeId;
    }
    public Long getCommunicationId() {
        return communicationId;
    }
    public void setCommunicationId(Long communicationId) {
        this.communicationId = communicationId;
    }

    public Iterable<Communication> getCommunications(){
        return communications;
    }

    public Comtype getComtype(){
        return comtype;
    }

}