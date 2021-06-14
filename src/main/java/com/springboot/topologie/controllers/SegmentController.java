package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Segment;
import com.springboot.topologie.models.Field;
import com.springboot.topologie.models.data.FieldDAO;
import com.springboot.topologie.models.data.SegmentDAO;
import com.springboot.topologie.models.forms.AddSegmentItemForm;
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
@RequestMapping (value = "segment")
public class SegmentController {

    @Autowired
    SegmentDAO segmentDAO;

    @Autowired
    FieldDAO fieldDAO;

    @RequestMapping (value = "")
    public String index(Model model) {
        model.addAttribute("title", "Segments");
        model.addAttribute("segments", segmentDAO.findAll());
        return "segment/index";
    }

    @RequestMapping (value = "add", method =RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add Segment");
        model.addAttribute(new Segment());
        return "segment/add";
    }

    @RequestMapping (value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Segment segment, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Segment");
            return "segment/add";
        }
        segmentDAO.save(segment);

        return "redirect:view/" + segment.getId();
    }

    @RequestMapping (value = "view/{segmentId}", method = RequestMethod.GET)
    public String viewSegment(Model model, @PathVariable int segmentId){
        Segment segment = segmentDAO.findById(segmentId).orElse(null);
        model.addAttribute("title", segment.getName());
        model.addAttribute("fields", segment.getFields());
        model.addAttribute("segmentId", segment.getId());

        return "segment/view";
    }

    @RequestMapping (value= "add-item/{segmentId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int segmentId){
        Segment segment = segmentDAO.findById(segmentId).orElse(null);
        AddSegmentItemForm form = new AddSegmentItemForm(
                fieldDAO.findAll(), segment);

        model.addAttribute("title", "Add item to segment: " + segment.getName());
        model.addAttribute("form", form);
        return "segment/add-item";
    }

    @RequestMapping (value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddSegmentItemForm form, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "segment/add-item";
        }

        Field theField = fieldDAO.findById(form.getFieldId()).orElse(null);
        Segment theSegment = segmentDAO.findById(form.getSegmentId()).orElse(null);
        theSegment.addItem(theField);
        segmentDAO.save(theSegment);

        return "redirect:/segment/view/" + theSegment.getId();
    }




}
