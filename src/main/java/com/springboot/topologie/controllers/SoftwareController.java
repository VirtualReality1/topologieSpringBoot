package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Communication;
import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;
import com.springboot.topologie.models.UMLCreator;
import com.springboot.topologie.models.data.CommunicationDAO;
import com.springboot.topologie.models.data.HardwareDAO;
import com.springboot.topologie.models.data.SoftwareDAO;
import com.springboot.topologie.models.forms.AddSoftwareItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping (value = "software")
public class SoftwareController {

    private SoftwareDAO softwareDAO;
    private HardwareDAO hardwareDAO;
    private UMLCreator umlCreator;

    public SoftwareController(SoftwareDAO softwareDAO, HardwareDAO hardwareDAO, UMLCreator umlCreator) {
        this.softwareDAO = softwareDAO;
        this.hardwareDAO = hardwareDAO;
        this.umlCreator = umlCreator;
    }


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Softwares");
        model.addAttribute("softwares", softwareDAO.findAll());
        return "software/index";
    }

//    @RequestMapping(value ="")
//    public String index2(Model model) throws IOException {
//
//        List<Software> software = softwareDAO.findAll();
//        for(Software s: software){
//            for (Communication c: s.getCommunication()) {
//                c.setPartner(softwareDAO.findById(c.getPartnerSoftwareId()).orElse(null));
//            }
//        }
//            String puml = umlCreator.buildContent(software);
//            umlCreator.generateContentAsPuml(puml);
//            return puml; // Set Breakpoint for Debugging here to check return in postman
//    }

    @RequestMapping (value = "add", method =RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add Software");
        model.addAttribute(new Software());
        return "software/add";
    }

    @RequestMapping (value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Software software, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Software");
            return "software/add";
    }
        softwareDAO.save(software);

        return "redirect:view/" + software.getId();
    }

    @RequestMapping (value = "view/{softwareId}", method = RequestMethod.GET)
    public String viewSoftware(Model model, @PathVariable Long softwareId) throws IOException {
           List<Software> softwareList = softwareDAO.findAll();

        Software software = softwareDAO.findById(softwareId).orElse(null);
        model.addAttribute("title", software.getName());
        model.addAttribute("hardwares", software.getHardwares());
        model.addAttribute("softwareId", software.getId());
        // model
        String content =  umlCreator.buildContent(softwareList);
        umlCreator.generateContentAsPuml(content);
        model.addAttribute("plantuml", content);
      //  umlCreator.generatePNGFromPuml(content);
        return "software/view";
    }

    @RequestMapping (value= "add-item/{softwareId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable Long softwareId){
        Software software = softwareDAO.findById(softwareId).orElse(null);
        AddSoftwareItemForm form = new AddSoftwareItemForm(
                hardwareDAO.findAll(), software);

        model.addAttribute("title", "Add item to software: " + software.getName());
        model.addAttribute("form", form);
        return "software/add-item";
    }

    @RequestMapping (value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddSoftwareItemForm form, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "software/add-item";
        }

        Hardware theHardware = hardwareDAO.findById(form.getHardwareId()).orElse(null);
        Software theSoftware = softwareDAO.findById(form.getSoftwareId()).orElse(null);
        theSoftware.addItem(theHardware);
        softwareDAO.save(theSoftware);

        return "redirect:/software/view/" + theSoftware.getId();
    }

}
