package com.dreamteam.SchoolSite.controller;

import com.dreamteam.SchoolSite.models.Career;
import com.dreamteam.SchoolSite.models.SchoolParty;
import com.dreamteam.SchoolSite.models.Teachers;
import com.dreamteam.SchoolSite.repositories.CareerRepository;
import com.dreamteam.SchoolSite.repositories.TeachersRepository;
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
public class TeachersController {

    @Autowired
    private TeachersRepository teachersRepository;

    @GetMapping("/teachers")
    public String teachersMain(Model model)
    {
        Iterable<Teachers> teachers = teachersRepository.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    @GetMapping("/teachers/add")
    public String teachersAdd(Model model)
    {
        return "teachers_add";
    }

    @PostMapping("/teachers/add")
    public String teachersPostAdd(@RequestParam String fullName,
                                  @RequestParam String description,
                                  @RequestParam String imageLink, Model model)
    {
        Teachers teachers = new Teachers(fullName,description,imageLink);
        teachersRepository.save(teachers);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}/edit")
    public String teachersEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!teachersRepository.existsById(id))
        {
            return "redirect:/teachers";
        }

        Optional<Teachers> teachers = teachersRepository.findById(id);
        ArrayList<Teachers> res = new ArrayList<>();
        teachers.ifPresent(res::add);
        model.addAttribute("teachers", res);
        return "teachers_edit";
    }

    @PostMapping("/teachers/{id}/edit")
    public String careerPostUpdate(@PathVariable(value = "id") long id,
                                   @RequestParam String fullName,
                                   @RequestParam String imageLink,
                                   @RequestParam String description,Model model)
    {
        Teachers teachers = teachersRepository.findById(id).orElseThrow(IllegalStateException::new);

        teachers.setFullName(fullName);
        teachers.setImageLink(imageLink);
        teachers.setDescription(description);
        teachersRepository.save(teachers);

        return "redirect:/teachers";
    }

    @PostMapping("/teachers/{id}/remove")
    public String teachersPostDelete(@PathVariable(value = "id") long id, Model model)
    {
        Teachers teachers = teachersRepository.findById(id).orElseThrow(IllegalStateException::new);
        teachersRepository.delete(teachers);

        return "redirect:/teachers";
    }
}