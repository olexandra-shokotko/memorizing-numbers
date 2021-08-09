package com.example.memorizingnumbers.controller;

import com.example.memorizingnumbers.domain.Role;
import com.example.memorizingnumbers.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

//    @PostMapping("/main")
//    public String storePhone(User user, Map<String, Object> model) {
//        User userFromDb = userRepo.findByUsername(user.getUsername());
//
//        if (userFromDb != null) {
//            model.put("message", "User already exists!");
//            return "registration";
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepo.save(user);
//
//        return "redirect:/login";
//    }
}