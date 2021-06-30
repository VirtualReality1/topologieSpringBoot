package com.springboot.topologie.services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.springboot.topologie.models.Communication;
import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Segment;
import com.springboot.topologie.models.Software;
import com.springboot.topologie.models.data.SoftwareDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UMLCreator {

    private SoftwareDAO softwareDAO;

    Logger logger = LoggerFactory.getLogger(UMLCreator.class);


    @Autowired
    public UMLCreator(SoftwareDAO softwareDAO) {
        this.softwareDAO = softwareDAO;
    }

    public String buildContent(Software s) throws NullPointerException {


        /**
        Created software objects are added into an Arraylist to avoid creation of 2 Objects with the same name.
         Necessary since PlantUML throws an error if the object is already declared. If the object already exists
         information is added to the existing one.
         */
        List<Software> alreadycreated = new ArrayList<>();


        /**
         Created communication objects are added into an Arraylist to avoid creation of 2 Objects with the same name.
         Necessary since PlantUML throws an error if the object is already declared. If the object already exists
         information is added to the existing one.
         */
        List<Communication> alreadythere = new ArrayList<>();


        /**
         * Start of content generation
         */
        String content = "@startuml\n";

        content += "object " + s.getType() + "\n";
        alreadycreated.add(s);

        /**
         * Retrieve every communication from database that each software has.
         */
        for (Communication c : s.getCommunication()) {

            /**
             * Find out who the Software is communication with / who the receiver/partner Software of the communication is
             * and create plantUML objects for each one if it doesn't exist already
             */

            Software partnerSoftware = softwareDAO.findById(c.getPartnerSoftwareId()).get();

            if (!(alreadycreated.contains(partnerSoftware)))
                content += "object " + partnerSoftware.getType() + "\n";

            //partner software content

            content += partnerSoftware.getType() + " : ID : " + partnerSoftware.getId() + "\n";
            content += partnerSoftware.getType() + " : " + partnerSoftware.getName() + "\n";
            content += partnerSoftware.getType() + " : " + partnerSoftware.getVersion() + "\n";
            content += partnerSoftware.getType() + " : " + partnerSoftware.getOsName() + "\n";


            alreadycreated.add(partnerSoftware);

            /**
             * Retrieve Hardware information for each Software
             */
            for (Hardware h : partnerSoftware.getHardwares()) {
                content += partnerSoftware.getType() + " : " + h.getIp() + "\n";
                content += partnerSoftware.getType() + " : Server:  " + h.getName() + "\n";
            }

            /**
             * Check if the receiver communicates back to the sender, if so create objects
             */
            for (Communication ce : partnerSoftware.getCommunication()) {
                Software softwaree = softwareDAO.findById(ce.getPartnerSoftwareId()).get();
                if (!(alreadycreated.contains(softwaree))) {
                    content += "object " + softwaree.getType() + "\n";

                    //partner software content

                    content += softwaree.getType() + " : ID : " + softwaree.getId() + "\n";
                    content += softwaree.getType() + " : " + softwaree.getName() + "\n";
                    content += softwaree.getType() + " : " + softwaree.getVersion() + "\n";
                    content += softwaree.getType() + " : " + softwaree.getOsName() + "\n";
                    for (Hardware h : softwaree.getHardwares()) {
                        content += softwaree.getType() + " : " + h.getIp() + "\n";
                        content += softwaree.getType() + " : Server: " + h.getName() + "\n";
                    }
                    alreadycreated.add(softwaree);

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

                /**
                 * See which segments are linked to the message type used in the communication
                 */

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
                if (!alreadythere.contains(c) && c.getTrigger() != null) {
                    content += "object " + c.getName() + "\n";
                    content += c.getName() + " : Name : " + c.getMessagetype().toString() + "\n";
                    content += c.getName() + " : ID : " + c.getId() + "\n";
                    content += c.getName() + " : Channel : " + c.getChannel() + "\n";
                    content += c.getName() + " : Comtype : " + c.getComtype() + "\n";
                }
                if(!(c.getTrigger() == null)){
                    content += s.getType() + " --|> " + c.getName() + ":" + c.getTrigger() + "\n";
                } else {
                    content += s.getType() + " --|> " + c.getName() + "\n";
                }

            }
            content += c.getName() + " --|> " + partnerSoftware.getType() + "\n";


            /**
             * See which segments are linked to the message type used in the communication
             */
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
            content += s.getType() + " : Server: " + h.getName() + "\n";
        }


        /**
         * End of puml String
         */

        content += "@enduml";
        return content;
    }


    /**
     * Takes generated content for each software and saves it as a .puml file and translates it into a .png for
     * diplay purposes
     *
     * @param software
     * @throws IOException
     */

    public void generateContentAsPuml(List<Software> software) throws IOException {

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




