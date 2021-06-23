package com.springboot.topologie.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.springboot.topologie.models.data.CommunicationDAO;
import com.springboot.topologie.models.data.SoftwareDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UMLCreator {

    private SoftwareDAO softwareDAO;
    private CommunicationDAO communicationDAO;

    Logger logger = LoggerFactory.getLogger(UMLCreator.class);


    @Autowired
    public UMLCreator (SoftwareDAO softwareDAO, CommunicationDAO communicationDAO){
        this.softwareDAO = softwareDAO;
    }

    public String buildContent(Software s) {

              String content = "@startuml\n";
              content += "object " + s.getType() + "\n";

              for (Communication c : s.getCommunication()) {
                  Software partnerSoftware = softwareDAO.findById(c.getPartnerSoftwareId()).get();

                      content += "object "+ partnerSoftware.getType() + "\n";

                    //partner software content

                  content += partnerSoftware.getType() + " : ID : " + partnerSoftware.getId() + "\n";
                  content += partnerSoftware.getType() + " : " + partnerSoftware.getName() + "\n";
                  content += partnerSoftware.getType() + " : " + partnerSoftware.getVersion() + "\n";
                  content += partnerSoftware.getType() + " : " + partnerSoftware.getOsName() + "\n";
                  for (Hardware h : s.getHardwares()) {
                      content += partnerSoftware.getType() + " : " + h.getIp() + "\n";
                  }

             //   Communication sCom = communicationDAO.findById(partnerSoftware.getId()).get();

                  //communication content
                  content += "object " + c.getName() + "\n";
                  //  content += "object " + sCom.getName() + "\n";
                  content += c.getName() + " : Name : " + c.getMessagetype().toString() + "\n";
                  content += c.getName() + " : ID : " + c.getId() + "\n";
                  content += c.getName() + " : Channel : " +c.getChannel() + "\n";
                  content += c.getName() + " : Comtype : " +c.getComtype() + "\n";
                  //content += c.getName() + " : Trigger : " + c.getTrigger() + "\n";

                  // communication from sender to receiver
                  content += s.getType() + " --|> " + c.getName() +":" + c.getTrigger() + "\n";
                  content += c.getName() + " --|> " + partnerSoftware.getType() + "\n";

                  content += s.getType() + " <|-- " + c.getName() +":" + c.getTrigger() + "\n";
                  content += c.getName() + " <|-- " + partnerSoftware.getType() + "\n";

                 // content += partnerSoftware.getType() + " --|> " + s.getType() + "\n";
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
//
//        public void generateContentAsPuml(String content, List<Software> software) throws IOException {
//        for(Software s : software ){
//                FileWriter fileWriter = new FileWriter("static/puml/" + s.getType() + ".puml");
//                PrintWriter out = new PrintWriter(fileWriter);
//                out.append(content);
//                out.close();
//
//                FileOutputStream png = new FileOutputStream(new File("static/png/" + s.getType() + ".png"));
//                SourceStringReader reader = new SourceStringReader(content);
//                String desc = reader.outputImage(png).getDescription();
//            }
//        }

    public void generateContentAsPuml(List<Software> software) throws IOException {

        for(Software s : software ){
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


//       public String generatePNGFromPuml(String content) throws IOException {
//           FileOutputStream png = new FileOutputStream(new File("static/png/CIS.png"));
//           SourceStringReader reader = new SourceStringReader(content);
//           String desc = reader.outputImage(png).getDescription();
//           return desc;
//       }



