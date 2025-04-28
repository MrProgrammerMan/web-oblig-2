package com.oslomet.mrprogrammerman.weboblig2.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/logout/{userid}")
    public String hello(@PathVariable String userid) {
        return "Logged out " + userid;
    }

    @PostMapping("/login/{userid}")
    public String login(@PathVariable String userid) {
        return "Logged in as " + userid + "!";
    }

    @PostMapping("/signup/{userid}")
    public String signup(@RequestBody String userid) {
        return "Signed up as " + userid + "!";
    }
}