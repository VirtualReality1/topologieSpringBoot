package com.springboot.topologie.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "communication")
public class Communication {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=3, max=15)
    private int partnerSoftwareId;


    @ManyToMany
    private List<Comtype> comtypes;

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

    public int getPartnerSoftwareId() {
        return partnerSoftwareId;
    }
}