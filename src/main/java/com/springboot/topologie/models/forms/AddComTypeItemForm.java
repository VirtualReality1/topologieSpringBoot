package com.springboot.topologie.models.forms;

import com.springboot.topologie.models.*;

import javax.validation.constraints.NotNull;

public class AddComTypeItemForm {
    @NotNull
    private int comtypeId;

    @NotNull
    private int communicationId;

    private Iterable<Communication> communications;
    private Comtype comtype;

    public AddComTypeItemForm(){ }

    public AddComTypeItemForm(Iterable <Communication> communications, Comtype comtype) {
        this.communications = communications;
        this.comtype = comtype;
    }

    public int getComtypeId() {
        return comtypeId;
    }

    public void setComtypeId(int comtypeId) {
        this.comtypeId = comtypeId;
    }
    public int getCommunicationId() {
        return communicationId;
    }
    public void setCommunicationId(int communicationId) {
        this.communicationId = communicationId;
    }

    public Iterable<Communication> getCommunications(){
        return communications;
    }

    public Comtype getComtype(){
        return comtype;
    }

}