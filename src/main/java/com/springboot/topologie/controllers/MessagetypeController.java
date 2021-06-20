package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Messagetype;
import com.springboot.topologie.models.data.HardwareDAO;
import com.springboot.topologie.models.data.MessagetypeDAO;
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
@RequestMapping(value = "messagetype")
public class MessagetypeController {

    @Autowired
    MessagetypeDAO messagetypeDAO;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Messagetypes");  //passes titel "messagestypes" to tag ${title} in hardware index.html file.
        model.addAttribute("messagetypes", messagetypeDAO.findAll());
        return "messagetype/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMessagetypeForm(Model model) {
        model.addAttribute("title", "Add Messagetype");
        model.addAttribute(new Messagetype());
        return "messagetype/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Messagetype messagetype, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Messagetype");
            return "messagetype/add";
        }
        messagetypeDAO.save(messagetype);
        return "redirect:view/" + messagetype.getId();
    }

    @RequestMapping(value = "view/{messagetypeId}", method = RequestMethod.GET)
    public String viewMessagetype(Model model, @PathVariable Long messagetypeId) {
        Messagetype messagetype = messagetypeDAO.findById(messagetypeId).orElse(null);
        model.addAttribute("title", messagetype.getName());
        model.addAttribute("messagetypeId", messagetype.getId());
        return "messagetype/view";
    }
}
