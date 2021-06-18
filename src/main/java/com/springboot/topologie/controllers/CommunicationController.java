package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Communication;
import com.springboot.topologie.models.Comtype;
import com.springboot.topologie.models.data.ComTypeDAO;
import com.springboot.topologie.models.data.CommunicationDAO;
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
@RequestMapping (value = "communication")
public class CommunicationController {

    @Autowired
    CommunicationDAO communicationDAO;

    @Autowired
    ComTypeDAO comTypeDAO;

    @RequestMapping (value = "")
    public String index(Model model) {
        model.addAttribute("title", "Communications");
        model.addAttribute("communications", communicationDAO.findAll());
        return "communication/index";
    }

//    @RequestMapping (value = "add", method =RequestMethod.GET)
//    public String add(Model model){
//        model.addAttribute("title", "Add Communication");
//        model.addAttribute(new Communication());
//        return "communication/add";
//    }

//    @RequestMapping (value = "add", method = RequestMethod.POST)
//    public String add(Model model, @ModelAttribute @Valid Communication communication, Errors errors){
//
//        if(errors.hasErrors()){
//            model.addAttribute("title", "Add Communication");
//            return "communication/add";
//        }
//        communicationDAO.save(communication);
//
//        return "redirect:view/" + communication.getId();
//    }

//    @RequestMapping (value = "view/{communicationId}", method = RequestMethod.GET)
//    public String viewCommunication(Model model, @PathVariable int communicationId){
//        Communication communication = communicationDAO.findById(communicationId).orElse(null);
//        model.addAttribute("title", communication.getName());
//        model.addAttribute("comtypes", communication.getComtypes());
//        model.addAttribute("communicationId", communication.getId());
//
//        return "communication/view";
//    }

//    @RequestMapping (value= "add-item/{communicationId}", method = RequestMethod.GET)
//    public String addItem(Model model, @PathVariable int communicationId){
//        Communication communication = communicationDAO.findById(communicationId).orElse(null);
//        AddCommunicationItemForm form = new AddCommunicationItemForm(
//                comTypeDAO.findAll(), communication);
//
//        model.addAttribute("title", "Add item to communication: " + communication.getName());
//        model.addAttribute("form", form);
//        return "communication/add-item";
//    }
//
//    @RequestMapping (value = "add-item", method = RequestMethod.POST)
//    public String addItem(Model model, @ModelAttribute @Valid AddCommunicationItemForm form, Errors errors){
//
//        if(errors.hasErrors()){
//            model.addAttribute("form", form);
//            return "communication/add-item";
//        }
//
//        Comtype theComtype = comTypeDAO.findById(form.getComtypeId()).orElse(null);
//        Communication theCommunication = communicationDAO.findById(form.getCommunicationId()).orElse(null);
//        theCommunication.addItem(theComtype);
//        communicationDAO.save(theCommunication);
//
//        return "redirect:/communication/view/" + theCommunication.getId();
//    }




}
