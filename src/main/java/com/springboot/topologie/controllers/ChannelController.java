package com.springboot.topologie.controllers;

import com.springboot.topologie.models.Channel;
import com.springboot.topologie.models.data.ChannelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping (value = "channel")
public class ChannelController {

    @Autowired
    ChannelDAO channelDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Channel");  //passes titel "fields" to tag ${title} in channel index.html file.
        model.addAttribute("channels", channelDao.findAll());
        return "channel/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddChannelForm(Model model) {
        model.addAttribute("title", "Add Channel");
        model.addAttribute(new Channel());
        return "channel/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Channel channel, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Channel");
            return "channel/add";
        }
        channelDao.save(channel);
        return "redirect:view/" + channel.getId();
    }

    @RequestMapping(value = "view/{channelId}", method = RequestMethod.GET)
    public String viewChannel(Model model, @PathVariable Long channelId) {
        Channel channel = channelDao.findById(channelId).orElse(null);
        model.addAttribute("title", channel.getName());
        model.addAttribute("channelId", channel.getId());
        return "channel/view";
    }
}