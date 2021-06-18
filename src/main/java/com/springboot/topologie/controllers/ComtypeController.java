package com.springboot.topologie.controllers;

import com.springboot.topologie.models.*;
import com.springboot.topologie.models.data.ChannelDAO;
import com.springboot.topologie.models.data.ComTypeDAO;
import com.springboot.topologie.models.data.CommunicationDAO;
import com.springboot.topologie.models.forms.AddComTypeItemForm;
import com.springboot.topologie.models.forms.AddFieldItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping (value = "comtype")
public class ComtypeController {

    @Autowired
    ComTypeDAO comtypeDao;

    @Autowired
    CommunicationDAO communicationDAO;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Comtype");  //passes titel "comtype" to tag ${title} in channel index.html file.
        model.addAttribute("comtypes", comtypeDao.findAll());
        return "comtype/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddChannelForm(Model model) {
        model.addAttribute("title", "Add Comtype");
        model.addAttribute(new Comtype());
        return "comtype/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Comtype comtype, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Comtype");
            return "comtype/add";
        }
        comtypeDao.save(comtype);
        return "redirect:view/" + comtype.getId();
    }

    @RequestMapping(value = "view/{comtypeId}", method = RequestMethod.GET)
    public String viewComtype(Model model, @PathVariable int comtypeId) {
        Comtype comtype = comtypeDao.findById(comtypeId).orElse(null);
        model.addAttribute("title", comtype.getName());
        model.addAttribute("communcations", comtype.getCommunications());
        model.addAttribute("comtypeId", comtype.getId());
        return "comtype/view";
    }

    @RequestMapping (value= "add-item/{comtypeId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int comtypeId){
        Comtype comtype = comtypeDao.findById(comtypeId).orElse(null);
        AddComTypeItemForm form = new AddComTypeItemForm(
                communicationDAO.findAll(), comtype);
        model.addAttribute("title", "Add item to comtype: " + comtype.getName());
        model.addAttribute("form", form);
        return "comtype/add-item";
    }

    @RequestMapping (value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddComTypeItemForm form, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "comtype/add-item";
        }

        Communication theCommunication = communicationDAO.findById(form.getCommunicationId()).orElse(null);
        Comtype theComtype = comtypeDao.findById(form.getComtypeId()).orElse(null);
        theComtype.addItem(theCommunication);
        comtypeDao.save(theComtype);

        return "redirect:/comtype/view/" + theComtype.getId();
    }
}