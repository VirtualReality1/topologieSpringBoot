package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Hardware;
import com.springboot.topologie.models.Software;
import com.springboot.topologie.models.data.HardwareDAO;
import com.springboot.topologie.models.data.SoftwareDAO;
import com.springboot.topologie.models.forms.AddHardwareItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping (value = "hardware")
public class HardwareController {
  // static ArrayList<String> hardwares = new ArrayList<>();
    @Autowired
    HardwareDAO hardwareDAO;

    @Autowired
    SoftwareDAO softwareDAO;

    @RequestMapping (value = "")
    public String index(Model model){
        model.addAttribute("title", "Hardwares");  //passes titel "hardwares" to tag ${title} in hardware index.html file.
        model.addAttribute("hardwares", hardwareDAO.findAll());
   return "hardware/index";
    }

    @RequestMapping(value = "add", method=RequestMethod.GET)
    public String displayAddHardwareForm(Model model){
        model.addAttribute("title", "Add Hardware");
        model.addAttribute(new Hardware());
        return "hardware/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Hardware hardware, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Hardware");
            return"hardware/add";
        }
        hardwareDAO.save(hardware);
        return "redirect:view/" + hardware.getId();
    }

    @RequestMapping (value = "view/{hardwareId}", method = RequestMethod.GET)
    public String viewHardware(Model model, @PathVariable int hardwareId){
        Hardware hardware = hardwareDAO.findById(hardwareId).orElse(null);
        model.addAttribute("title", hardware.getName());
        model.addAttribute("softwares",hardware.getSoftwares());
        model.addAttribute("hardwareId", hardware.getId());
        return "hardware/view";
    }

    @RequestMapping (value= "add-item/{hardwareId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int hardwareId){
        Hardware hardware = hardwareDAO.findById(hardwareId).orElse(null);
        AddHardwareItemForm form = new AddHardwareItemForm(
               softwareDAO.findAll(), hardware);

        model.addAttribute("title", "Add item to hardware: " + hardware.getName());
        model.addAttribute("form", form);
        return "hardware/add-item";
    }



    @RequestMapping (value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddHardwareItemForm form, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "hardware/add-item";
        }

        Software theSoftware = softwareDAO.findById(form.getSoftwareId()).orElse(null);
        Hardware theHardware = hardwareDAO.findById(form.getHardwareId()).orElse(null);
        theHardware.addItem(theSoftware);
        hardwareDAO.save(theHardware);

        return "redirect:/hardware/view/" + theHardware.getId();
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