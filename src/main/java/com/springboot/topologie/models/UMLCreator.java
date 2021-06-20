package com.springboot.topologie.models;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sourceforge.plantuml.SourceStringReader;

public class UMLCreator {

    Logger logger = LoggerFactory.getLogger(UMLCreator.class);


    public UMLCreator (){}


    String content;
    public String buildContent(Software software) throws IOException {

        content = "@startuml\n";
        content += "object " + software.getType() + "\n";
        content += "object " + software.getType() + "\n";
        content += "object " + software.getType() + "\n";
        content += "object " + software.getType() + "\n";
        content += "object " + "communication_1" + "\n";
        content += "object " + "communication_2" + "\n";
        content += "object " + "communication_3" + "\n";
        content += "object " + "communication_4" + "\n";
        content += "object " + "communication_5" + "\n";
        content += "communication_1 " + ":" + " Name: " + software.getCommunication()  +"\n";
        content += "communication_1 " + ":" + " ID: " + software.getCommunication() + "\n";
        content += "communication_2 " + ":" + " Name: " + software.getCommunication() + "\n";
        content += "communication_2 " + ":" + " ID: " + software.getCommunication() + "\n";
        content += "communication_3 " + ":" + " Name: " + software.getCommunication() + "\n";
        content += "communication_3 " + ":" + " ID: " + software.getCommunication() + "\n";
        content += "communication_4 " + ":" + " Name: " + software.getCommunication() + "\n";
        content += "communication_4 " + ":" + " ID: " + software.getCommunication() + "\n";
        content += "communication_5 " + ":" + " Name: " + software.getCommunication() + "\n";
        content += "communication_5 " + ":" + " ID: " + software.getCommunication() + "\n";

        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";

        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";

        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";

        content += software.getType() + " : " + "ID: " + software.getId() + "\n";
        content += software.getType() + " : " + "Name : " + software.getName() + "\n";
        content += software.getType() + " : " + "Version: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "Os: " + software.getVersion() + "\n";
        content += software.getType() + " : " + "IP: " + software.getHardwares() + "\n";

        content += software.getType() + " --|>" + "communication_1" + "\n";
        content += "communication_1" +  " --|> " + software.getType() + "\n";

        content += software.getType() + " --|>" + "communication_2" + "\n";
        content += "communication_2" +  " --|> " + software.getType() + "\n";

        content += software.getType() + " --|>" + "communication_2" + "\n";
        content += "communication_2" +  " --|> " + software.getType() + "\n";

        content += software.getType() + " --|>" + "communication_2" + "\n";
        content += "communication_2" +  " --|> " + software.getType() + "\n";

        content += software.getType() + " --|>" + "communication_2" + "\n";
        content += "communication_2" +  " --|> " + software.getType() + "\n";

        content += "@enduml";
        return content;
    }

        public void generateContentAsPuml() throws IOException {
            FileWriter fileWriter = new FileWriter("static/puml/Test.puml");
            PrintWriter out = new PrintWriter(fileWriter);
            out.append(content);
            out.close();
        }

//        @Deprecated
//       public void generatePNGFromPuml() throws FileNotFoundException {
//           FileOutputStream png = new FileOutputStream(new File("static/png/Test.png")) ;
//           SourceStringReader reader = new SourceStringReader(content);
//
//       }


}
