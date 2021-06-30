package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Software;
import com.springboot.topologie.services.UMLCreator;
import com.springboot.topologie.models.data.HardwareDAO;
import com.springboot.topologie.models.data.SoftwareDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping (value = "topologie")
public class TopologieController {

    private SoftwareDAO softwareDAO;
    private HardwareDAO hardwareDAO;
    private UMLCreator umlCreator;

    public TopologieController(SoftwareDAO softwareDAO, HardwareDAO hardwareDAO, UMLCreator umlCreator) {
        this.softwareDAO = softwareDAO;
        this.hardwareDAO = hardwareDAO;
        this.umlCreator = umlCreator;
    }

    @RequestMapping(value = "")
    public String index(Model model) throws IOException {
        List<Software> softwareList = softwareDAO.findAll();

        File f = new File("static/png");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        ArrayList<String> path = new ArrayList<String>();
        files.forEach(s -> path.add(s.toString().substring(6)));
        String test = "test";
        Pattern p = Pattern.compile("\\\\");
        for (int i = 0; i < path.size(); i++) {
            Matcher matcher = p.matcher(path.get(i));
            path.set(i, matcher.replaceAll("/"));
        }
        model.addAttribute("path", path);
        model.addAttribute("test", test.replace("e","a"));

        umlCreator.generateContentAsPuml(softwareList);
        return "topologie/view";
    }
}
