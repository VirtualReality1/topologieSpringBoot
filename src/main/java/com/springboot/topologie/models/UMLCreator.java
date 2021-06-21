package com.springboot.topologie.models;

import java.io.*;
import java.util.List;

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
    public UMLCreator (SoftwareDAO softwareDAO){
        this.softwareDAO = softwareDAO;
    }




    public String buildContent(List<Software> software) {

        String content = "@startuml\n";


//      for(Software s: software) {
          for (int i = 0; i < software.size(); i++) {
              content += "object " + software.get(i).getType() + "\n";
          }
          for (Software s : software) {
              for (Communication c : s.getCommunication()) {
                  content += "object " + c.getName() + "\n";
                  content += c.getName() + " : Name : " + c.getMessagetype().toString() + "\n";
                  content += c.getName() + " : ID : " + c.getId() + "\n";
              }
              for (Communication c : s.getCommunication()) {
                  content += s.getType() + " --|> " + c.getName() + "\n";
                  content += c.getName() + " --|> " + softwareDAO.findById(c.getPartnerSoftwareId()).get().getType() + "\n";
              }
              content += s.getType() + " : ID : " + s.getId() + "\n";
              content += s.getType() + " : " + s.getName() + "\n";
              content += s.getType() + " : " + s.getVersion() + "\n";
              content += s.getType() + " : " + s.getOsName() + "\n";
              for (Hardware h : s.getHardwares()) {
                  content += s.getType() + " : " + h.getIp() + "\n";
              }
          }

        content += "@enduml";
        return content;
    }

        public void generateContentAsPuml(String content,List<Software> software) throws IOException {
        for(Software s : software ){
            for (Communication c : s.getCommunication()) {
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


}
