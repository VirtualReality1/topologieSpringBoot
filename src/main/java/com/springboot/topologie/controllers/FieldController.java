package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.Segment;
import com.springboot.topologie.models.data.FieldDAO;
import com.springboot.topologie.models.data.SegmentDAO;
import com.springboot.topologie.models.forms.AddFieldItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping (value = "field")
public class FieldController {
    @Autowired
    FieldDAO fieldDAO;

    @Autowired
    SegmentDAO segmentDAO;

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
    public String viewField(Model model, @PathVariable Long fieldId){
        Field field = fieldDAO.findById(fieldId).orElse(null);
        model.addAttribute("title", field.getName());
        model.addAttribute("segments",field.getSegments());
        model.addAttribute("fieldId", field.getId());
        return "field/view";
    }

//    @RequestMapping (value = "view/{fieldId}", method = RequestMethod.GET)
//    public String viewField(Model model, @PathVariable int fieldId){
//        Field field = fieldDAO.findById(fieldId).orElse(null);
//        model.addAttribute("title", field.getName());
//        model.addAttribute("segments",field.getSegments());
//        model.addAttribute("fieldId", field.getId());
//        return "field/view";
//    }

    @RequestMapping (value= "add-item/{fieldId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable Long fieldId){
        Field field = fieldDAO.findById(fieldId).orElse(null);
        AddFieldItemForm form = new AddFieldItemForm(
                segmentDAO.findAll(), field);
        model.addAttribute("title", "Add item to field: " + field.getName());
        model.addAttribute("form", form);
        return "field/add-item";
    }

    @RequestMapping (value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddFieldItemForm form, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "field/add-item";
        }

        Segment theSegment = segmentDAO.findById(form.getSegmentId()).orElse(null);
        Field theField = fieldDAO.findById(form.getFieldId()).orElse(null);
        theField.addItem(theSegment);
        fieldDAO.save(theField);

        return "redirect:/field/view/" + theField.getId();
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