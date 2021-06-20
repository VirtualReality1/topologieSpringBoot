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


        for(Software s: software){
           content += "object " + s.getType() + "\n";
           for (Communication c : s.getCommunication()) {
               content += "object " + c.getName() + "\n";
               content += c.getName() + " : Name : " + c.getMessagetype().toString() + "\n";
               content += c.getName() + " : ID : " + c.getId() + "\n";
           }
           for (Communication c: s.getCommunication()){
               content += s.getType() + " --|> " + c.getName() + "\n";
               content += c.getName() + " --|> " + c.getPartnerSoftwareId() + "\n";
           }
            content += s.getType() + " : ID : " + s.getId() + "\n";
            content += s.getType() + " : Name : " + s.getName() + "\n";
            content += s.getType() + " : Version : " + s.getVersion() + "\n";
            content += s.getType() + " : OS : " + s.getOsName() + "\n";
                for(Hardware h: s.getHardwares()){
                    content += s.getType() + " : IP : " + h.getIp() + "\n";
                }
            }



//        content += "object " + software.getType() + "\n";
//        content += "object " + software.getType() + "\n";
//        content += "object " + software.getType() + "\n";
//        content += "object " + software.getType() + "\n";
//        content += "object " + "communication_1" + "\n";
//        content += "object " + "communication_2" + "\n";
//        content += "object " + "communication_3" + "\n";
//        content += "object " + "communication_4" + "\n";
//        content += "object " + "communication_5" + "\n";
//        content += "communication_1 " + ":" + " Name: " + software.getCommunication()  +"\n";
//        content += "communication_1 " + ":" + " ID: " + software.getCommunication() + "\n";
//        content += "communication_2 " + ":" + " Name: " + software.getCommunication() + "\n";
//        content += "communication_2 " + ":" + " ID: " + software.getCommunication() + "\n";
//        content += "communication_3 " + ":" + " Name: " + software.getCommunication() + "\n";
//        content += "communication_3 " + ":" + " ID: " + software.getCommunication() + "\n";
//        content += "communication_4 " + ":" + " Name: " + software.getCommunication() + "\n";
//        content += "communication_4 " + ":" + " ID: " + software.getCommunication() + "\n";
//        content += "communication_5 " + ":" + " Name: " + software.getCommunication() + "\n";
//        content += "communication_5 " + ":" + " ID: " + software.getCommunication() + "\n";
//
//        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
//        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
//        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";
//
//        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
//        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
//        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";
//
//        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
//        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
//        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";
//
//        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
//        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
//        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
//        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";
//
//        content += software.getType() + " --|>" + "communication_1" + "\n";
//        content += "communication_1" +  " --|> " + software.getType() + "\n";
//
//        content += software.getType() + " --|>" + "communication_2" + "\n";
//        content += "communication_2" +  " --|> " + software.getType() + "\n";
//
//        content += software.getType() + " --|>" + "communication_2" + "\n";
//        content += "communication_2" +  " --|> " + software.getType() + "\n";
//
//        content += software.getType() + " --|>" + "communication_2" + "\n";
//        content += "communication_2" +  " --|> " + software.getType() + "\n";
//
//        content += software.getType() + " --|>" + "communication_2" + "\n";
//        content += "communication_2" +  " --|> " + software.getType() + "\n";

        content += "@enduml";
        return content;
    }

        public void generateContentAsPuml(String content) throws IOException {
            FileWriter fileWriter = new FileWriter("static/puml/Test.puml");
            PrintWriter out = new PrintWriter(fileWriter);
            out.append(content);
            out.close();
        }


       public String generatePNGFromPuml(String content) throws FileNotFoundException {
           FileOutputStream png = new FileOutputStream(new File("static/png/Test.png")) ;
           SourceStringReader reader = new SourceStringReader(content);
           String desc = "";
            try {
                desc = reader.generateImage(png).toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return desc;
        }


}
