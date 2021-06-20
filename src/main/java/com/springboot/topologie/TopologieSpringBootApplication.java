package com.springboot.topologie;

import com.springboot.topologie.models.Software;
import com.springboot.topologie.models.UMLCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TopologieSpringBootApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TopologieSpringBootApplication.class, args);
        // UMLCreator umlCreator = new UMLCreator();
       // umlCreator.buildContent(new Software());
        //umlCreator.generateContentAsPuml();
    }
}
