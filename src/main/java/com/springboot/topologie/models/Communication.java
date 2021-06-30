package com.springboot.topologie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "communication")
public class Communication {
  //  private Software partner;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=3, max=15)
    private Long partnerSoftwareId;



    @ManyToOne
    @JoinColumn(name="comtype_id", referencedColumnName = "id")
    private Comtype comtype;

    @ManyToOne
    @JoinColumn(name="channel_id", referencedColumnName = "id")
    private Channel channel;




    @ManyToOne (optional = true)
    @JoinColumn(name="messagetype_id", referencedColumnName = "id")
    private Messagetype messagetype;

    @ManyToOne
    @JoinColumn(name="software_id", referencedColumnName = "id")
    private Software software;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="com_tr_trigger",
    joinColumns = {@JoinColumn(name = "communication_id", referencedColumnName = "id")}, inverseJoinColumns =
                  {@JoinColumn(name = "tr_trigger_id", referencedColumnName = "id")})
    private TrTrigger trigger;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Communication () {}

//    public void setPartner(Software partner){
//        this.partner = partner;
//    }
//
//    public Software getPartner(){
//        return this.partner;
//    }

    public Long getPartnerSoftwareId() {
        return partnerSoftwareId;
    }

    public Comtype getComtype() {
        return comtype;
    }

    public Messagetype getMessagetype() {
        return messagetype;
    }

    public Channel getChannel() {
        return channel;
    }

    public Software getSoftware() {
        return software;
    }

    public TrTrigger getTrigger() {
        return trigger;
    }
}