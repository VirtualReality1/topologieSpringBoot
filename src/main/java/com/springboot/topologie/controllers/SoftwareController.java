package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;
import com.springboot.topologie.models.data.HardwareDAO;
import com.springboot.topologie.models.data.SoftwareDAO;
import com.springboot.topologie.models.forms.AddSoftwareItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping (value = "software")
public class SoftwareController {

    @Autowired
    SoftwareDAO softwareDAO;

    @Autowired
    HardwareDAO hardwareDAO;

    @RequestMapping (value = "")
    public String index(Model model) {
        model.addAttribute("title", "Softwares");
        model.addAttribute("softwares", softwareDAO.findAll());
        return "software/index";
    }

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
    public String viewSoftware(Model model, @PathVariable int softwareId){
        Software software = softwareDAO.findById(softwareId).orElse(null);
        model.addAttribute("title", software.getName());
        model.addAttribute("hardwares", software.getHardwares());
        model.addAttribute("softwareId", software.getId());
        // model
        String content = "@startuml" + "\n" + software.getPumlName() + "\n" +"@enduml";
        model.addAttribute("plantuml", content);
        return "software/view";
    }

    @RequestMapping (value= "add-item/{softwareId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int softwareId){
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
