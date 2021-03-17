package com.dreamteam.SchoolSite.controller;

import com.dreamteam.SchoolSite.models.Role;
import com.dreamteam.SchoolSite.models.User;
import com.dreamteam.SchoolSite.repositories.UserRepository;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model)
    {
        model.addAttribute("users", userRepository.findAll());

        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model)
    {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "user_edit";
    }

    @PostMapping
    public String userSave(
                            @RequestParam String name,
                            @RequestParam Map<String,String> form,
                            @RequestParam("userId")  User user)
    {
        user.setName(name);

        Set<String > roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet())
        {
            if(roles.contains(key))
            {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/user";
    }
}
