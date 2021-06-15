package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;
import com.springboot.topologie.models.data.FieldDAO;
import com.springboot.topologie.models.data.HardwareDAO;
import com.springboot.topologie.models.forms.AddSoftwareItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping (value = "field")
public class FieldController {
    // static ArrayList<String> hardwares = new ArrayList<>();
    @Autowired
    FieldDAO fieldDAO;

    @RequestMapping (value = "")
    public String index(Model model){
        model.addAttribute("title", "Fields");  //passes titel "fields" to tag ${title} in hardware index.html file.
        model.addAttribute("fields", fieldDAO.findAll());
        return "field/index";
    }

    @RequestMapping(value = "add", method=RequestMethod.GET)
    public String displayAddFieldForm(Model model){
        model.addAttribute("title", "Add Field");
        model.addAttribute(new Field());
        return "field/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Field field, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Field");
            return"field/add";
        }
        fieldDAO.save(field);
        return "redirect:view/" + field.getId();
    }

    @RequestMapping (value = "view/{fieldId}", method = RequestMethod.GET)
    public String viewField(Model model, @PathVariable int fieldId){
        Field field = fieldDAO.findById(fieldId).orElse(null);
        model.addAttribute("title", field.getName());
        model.addAttribute("fieldId", field.getId());
        return "field/view";
    }



//
//    @RequestMapping(method = RequestMethod.POST,value="/add")
//    public Iterable<Hardware> addHardware(@RequestBody Hardware hardware){
//        hardwareDAO.save(hardware);
//        return hardwareDAO.findAll();
//    }


//
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAddHardwareForm(@RequestParam String hardwareName){
//        hardwares.add(hardwareName);
//
//        //Redirects to /hardware endpoint itself without additional params since we are already in the hardware controller
//        return "redirect:";
//    }
}