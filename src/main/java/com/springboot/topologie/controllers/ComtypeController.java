package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Channel;
import com.springboot.topologie.models.Comtype;
import com.springboot.topologie.models.data.ChannelDAO;
import com.springboot.topologie.models.data.ComTypeDAO;
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
        model.addAttribute("comtypeId", comtype.getId());
        return "comtype/view";
    }
}