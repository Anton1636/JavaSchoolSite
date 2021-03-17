package com.dreamteam.SchoolSite.service;

import com.dreamteam.SchoolSite.models.Role;
import com.dreamteam.SchoolSite.models.User;
import com.dreamteam.SchoolSite.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  MailSenderService mailSenderService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByUserName(name);
    }

    public boolean addUser(User user)
    {
        User userFormDb = userRepository.findByUserName(user.getUsername());

        if(userFormDb != null)
        {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        if(!StringUtils.isEmpty(user.getEmail()))
        {
            String message = String.format(
                    "Hello,%s! \n"+
                            "Welcome to our School.Please visit next link: http://localhost:8084/active/%s",
                    user.getUsername(),
                    user.getActivationCode());

            mailSenderService.send(user.getEmail(),"Activation code",message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if(user == null)
        {
            return  false;
        }

        user.setActivationCode(null);
        userRepository.save(user);

        return  true;
    }
}
