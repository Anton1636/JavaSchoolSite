package com.dreamteam.SchoolSite.controller;

import com.dreamteam.SchoolSite.models.Career;
import com.dreamteam.SchoolSite.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller("/career")
public class CareerController {

    @Autowired
    private CareerRepository careerRepository;

    @GetMapping("/career")
    public String careerMain(Model model)
    {
        Iterable<Career> careers = careerRepository.findAll();
        model.addAttribute("careers", careers);
        return "career";
    }

    @GetMapping("/career/add")
    public String careerAdd(Model model)
    {
        return "career";
    }

    @PostMapping("/career/add")
    public String careerPostAdd(@RequestParam String description, @RequestParam String salary,Model model)
    {
        Career career = new Career(description,salary);
        careerRepository.save(career);
        return "redirect:/career";
    }

    @GetMapping("/career/{id}/edit")
    public String careerEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!careerRepository.existsById(id))
        {
            return "redirect:/career";
        }

        Optional<Career> career = careerRepository.findById(id);
        ArrayList<Career> res = new ArrayList<>();
        career.ifPresent(res::add);
        model.addAttribute("career", res);
        return "career";
    }

    @PostMapping("/career/{id}/edit")
    public String careerPostUpdate(@PathVariable(value = "id") long id,
                                   @RequestParam String description,
                                   @RequestParam String salary,Model model)
    {
        Career career = careerRepository.findById(id).orElseThrow(IllegalStateException::new);

        career.setDescription(description);
        career.setSalary(salary);
        careerRepository.save(career);

        return "redirect:/career";
    }

    @PostMapping("/career/{id}/remove")
    public String careerPostDelete(@PathVariable(value = "id") long id, Model model)
    {
        Career career = careerRepository.findById(id).orElseThrow(IllegalStateException::new);
        careerRepository.delete(career);

        return "redirect:/career";
    }
}