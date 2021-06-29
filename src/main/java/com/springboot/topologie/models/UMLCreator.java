package com.springboot.topologie.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.topologie.models.data.CommunicationDAO;
import com.springboot.topologie.models.data.MessagetypeDAO;
import com.springboot.topologie.models.data.SoftwareDAO;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UMLCreator {

    private SoftwareDAO softwareDAO;
    private CommunicationDAO communicationDAO;
    private MessagetypeDAO messagetypeDAO;

    Logger logger = LoggerFactory.getLogger(UMLCreator.class);


    @Autowired
    public UMLCreator(SoftwareDAO softwareDAO, CommunicationDAO communicationDAO, MessagetypeDAO messagetypeDAO) {
        this.softwareDAO = softwareDAO;
    }

    public String buildContent(Software s) throws NullPointerException{

        List<Software> alreadycreated = new ArrayList<>();
        List<Communication> alreadythere = new ArrayList<>();

        String content = "@startuml\n";

        content += "object " + s.getType() + "\n";
        alreadycreated.add(s);


        for (Communication c : s.getCommunication()) {
            Software partnerSoftware = softwareDAO.findById(c.getPartnerSoftwareId()).get();
            if (!(alreadycreated.contains(partnerSoftware)))
                content += "object " + partnerSoftware.getType() + "\n";
            //partner software content

            content += partnerSoftware.getType() + " : ID : " + partnerSoftware.getId() + "\n";
            content += partnerSoftware.getType() + " : " + partnerSoftware.getName() + "\n";
            content += partnerSoftware.getType() + " : " + partnerSoftware.getVersion() + "\n";
            content += partnerSoftware.getType() + " : " + partnerSoftware.getOsName() + "\n";

            for (Hardware h : s.getHardwares()) {
                content += partnerSoftware.getType() + " : " + h.getIp() + "\n";
            }
            alreadycreated.add(partnerSoftware);

            for (Communication ce : partnerSoftware.getCommunication()) {
                Software softwaree = softwareDAO.findById(ce.getPartnerSoftwareId()).get();
                if (!(alreadycreated.contains(softwaree))) {
                    content += "object " + softwaree.getType() + "\n";

                    //partner software content

                    content += softwaree.getType() + " : ID : " + softwaree.getId() + "\n";
                    content += softwaree.getType() + " : " + softwaree.getName() + "\n";
                    content += softwaree.getType() + " : " + softwaree.getVersion() + "\n";
                    content += softwaree.getType() + " : " + softwaree.getOsName() + "\n";
                    alreadycreated.add(softwaree);
                    for (Hardware h : partnerSoftware.getHardwares()) {
                        content += softwaree.getType() + " : " + h.getIp() + "\n";
                    }
                   }

                //communication content
                if (!(alreadythere.contains(c) || alreadythere.contains(ce))) {
                    content += "object " + c.getName() + "\n";
                    content += c.getName() + " : Name : " + c.getMessagetype().toString() + "\n";
                    content += c.getName() + " : ID : " + c.getId() + "\n";
                    content += c.getName() + " : Channel : " + c.getChannel() + "\n";
                    content += c.getName() + " : Comtype : " + c.getComtype() + "\n";
                    alreadythere.add(c);
                }

                // communication from sender to receiver


                if (!(alreadythere.contains(c) && alreadythere.contains(ce))) {
                    content += "object " + ce.getName() + "\n";
                    content += ce.getName() + " : Name : " + ce.getMessagetype().toString() + "\n";
                    content += ce.getName() + " : ID : " + ce.getId() + "\n";
                    content += ce.getName() + " : Channel : " + ce.getChannel() + "\n";
                    content += ce.getName() + " : Comtype : " + ce.getComtype() + "\n";
                    alreadythere.add(ce);
                }
                content += partnerSoftware.getType() + " --|> " + ce.getName() + "\n";

                content += ce.getName() + " --|> " + softwaree.getType() + "\n";
                alreadythere.add(ce);

                for (Segment se : ce.getMessagetype().getSegment())
                    content += ce.getName() + " : Segment : " + se.getName() + "\n";
            }

            if (c.getTrigger() == null && !alreadythere.contains(c)) {
                content += "object " + c.getName() + "\n";
                content += c.getName() + " : Name : " + c.getMessagetype().toString() + "\n";
                content += c.getName() + " : ID : " + c.getId() + "\n";
                content += c.getName() + " : Channel : " + c.getChannel() + "\n";
                content += c.getName() + " : Comtype : " + c.getComtype() + "\n";

            } else {
                if (!alreadythere.contains(c)) {
                    content += "object " + c.getName() + "\n";
                    content += c.getName() + " : Name : " + c.getMessagetype().toString() + "\n";
                    content += c.getName() + " : ID : " + c.getId() + "\n";
                    content += c.getName() + " : Channel : " + c.getChannel() + "\n";
                    content += c.getName() + " : Comtype : " + c.getComtype() + "\n";
                }
                content += s.getType() + " --|> " + c.getName() + ":" + c.getTrigger() + "\n";
            }
            content += c.getName() + " --|> " + partnerSoftware.getType() + "\n";

            for (Segment se : c.getMessagetype().getSegment())
                content += c.getName() + " : Segment : " + se.getName() + "\n";
        }

            //software content
            content += s.getType() + " : ID : " + s.getId() + "\n";
            content += s.getType() + " : " + s.getName() + "\n";
            content += s.getType() + " : " + s.getVersion() + "\n";
            content += s.getType() + " : " + s.getOsName() + "\n";

            for (Hardware h : s.getHardwares()) {
                content += s.getType() + " : " + h.getIp() + "\n";
            }

            content += "@enduml";
            return content;
        }


        public void generateContentAsPuml (List < Software > software) throws IOException {

            for (Software s : software) {
                String content = buildContent(s);
                FileWriter fileWriter = new FileWriter("static/puml/" + s.getType() + ".puml");
                PrintWriter out = new PrintWriter(fileWriter);
                out.append(content);
                out.close();


                FileOutputStream png = new FileOutputStream(new File("static/png/" + s.getType() + ".png"));
                SourceStringReader reader = new SourceStringReader(content);
                String desc = reader.outputImage(png).getDescription();
            }
        }

}




