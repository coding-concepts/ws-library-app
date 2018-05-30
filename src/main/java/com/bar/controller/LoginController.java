package com.bar.controller;

import com.bar.exception.ValidationException;
import com.bar.model.Greeting;
import com.bar.model.LoginRequest;
import com.bar.model.UserProfile;
import com.bar.service.ServiceFactory;
import com.bar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LoginController {


    //private UserService userService = ServiceFactory.getUserService();
    @Autowired
    private UserService userService;


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public UserProfile login(@RequestBody LoginRequest reuest) throws ValidationException {
        System.out.println("User = "+reuest.getUserName());
        System.out.println("Pass = "+reuest.getPassword());
        UserProfile profile  = userService.validateUser(reuest.getUserName(), reuest.getPassword());
        return profile;
//        return new Greeting(counter.incrementAndGet(),
//                String.format(template, reuest.getUserName()));


    }

}
