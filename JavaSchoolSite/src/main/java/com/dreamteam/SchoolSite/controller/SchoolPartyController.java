package com.dreamteam.SchoolSite.controller;

import com.dreamteam.SchoolSite.models.Career;
import com.dreamteam.SchoolSite.models.Schedule;
import com.dreamteam.SchoolSite.models.SchoolParty;
import com.dreamteam.SchoolSite.repositories.SchoolPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SchoolPartyController {

    @Autowired
    private SchoolPartyRepository schoolPartyRepository;

    @GetMapping("/schoolParty")
    public String schoolPartyMain(Model model)
    {
        Iterable<SchoolParty> schoolParties = schoolPartyRepository.findAll();
        model.addAttribute("schoolParties", schoolParties);
        return "schoolParty";
    }

    @GetMapping("/schoolParty/add")
    public String schoolPartyAdd(Model model)
    {
        return "schoolParty_add";
    }

    @PostMapping("/schoolParty/add")
    public String schoolPartyPostAdd(@RequestParam String fullDate,
                                     @RequestParam String description,
                                     @RequestParam String imageLink, Model model)
    {
        SchoolParty schoolParty = new SchoolParty(fullDate,description,imageLink);
        schoolPartyRepository.save(schoolParty);
        return "redirect:/schoolParty";
    }

    @GetMapping("/schoolParty/{id}/edit")
    public String schoolPartyEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!schoolPartyRepository.existsById(id))
        {
            return "redirect:/schoolParty";
        }

        Optional<SchoolParty> schoolParty = schoolPartyRepository.findById(id);
        ArrayList<SchoolParty> res = new ArrayList<>();
        schoolParty.ifPresent(res::add);
        model.addAttribute("schoolParty", res);
        return "schoolParty_edit";
    }

    @PostMapping("/schoolParty/{id}/edit")
    public String careerPostUpdate(@PathVariable(value = "id") long id,
                                   @RequestParam String fullDate,
                                   @RequestParam String description,
                                   @RequestParam String imageLink,Model model)
    {
        SchoolParty schoolParty = schoolPartyRepository.findById(id).orElseThrow(IllegalStateException::new);

        schoolParty.setDescription(fullDate);
        schoolParty.setDescription(imageLink);
        schoolParty.setDescription(description);
        schoolPartyRepository.save(schoolParty);

        return "redirect:/schoolParty";
    }

    @PostMapping("/schoolParty/{id}/remove")
    public String schoolPartyPostDelete(@PathVariable(value = "id") long id, Model model)
    {
        SchoolParty schoolParty = schoolPartyRepository.findById(id).orElseThrow(IllegalStateException::new);
        schoolPartyRepository.delete(schoolParty);

        return "redirect:/schoolParty";
    }
}