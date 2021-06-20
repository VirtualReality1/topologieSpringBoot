package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.TrTrigger;
import com.springboot.topologie.models.data.TrTriggerDAO;
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
@RequestMapping(value = "trigger")
public class TrTriggerController {
@Autowired
    TrTriggerDAO trTriggerDAO;

    @RequestMapping (value = "")
    public String index(Model model){
        model.addAttribute("title", "Trigger");  //passes titel "fields" to tag ${title} in hardware index.html file.
        model.addAttribute("triggers", trTriggerDAO.findAll());
        return "trigger/index";
    }

    @RequestMapping(value = "add", method=RequestMethod.GET)
    public String displayAddTriggerForm(Model model){
        model.addAttribute("title", "Add Trigger");
        model.addAttribute("trigger",new TrTrigger());
        return "trigger/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid TrTrigger trigger, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Trigger");
            return"trigger/add";
        }
        trTriggerDAO.save(trigger);
        return "redirect:view/" + trigger.getId();
    }

    @RequestMapping (value = "view/{triggerId}", method = RequestMethod.GET)
    public String viewTrigger(Model model, @PathVariable Long triggerId){
        TrTrigger trigger = trTriggerDAO.findById(triggerId).orElse(null);
        model.addAttribute("title", trigger.getName());
        model.addAttribute("triggerId", trigger.getId());
        return "trigger/view";
    }

}
