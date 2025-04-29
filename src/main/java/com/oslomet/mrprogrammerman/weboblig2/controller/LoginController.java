package com.oslomet.mrprogrammerman.weboblig2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/verification")
    public String admin() {
        return "verification";
    }

    @PostMapping("/verifyid")
    @ResponseBody
    public PersonResponse verifyId(@RequestParam String id, @RequestParam String barcode, Model model) {
        System.out.println("ID: " + id);

        List<Person> personList = Person.getMockData();
        Person personFound = personList.stream()
            .filter(person -> String.valueOf(person.id).equals(id) &&
                            String.valueOf(person.barcodeId).equals(barcode))
            .findFirst()
            .orElse(null);
        
        boolean found = false;
        String errorMessage = null;
        if (personFound == null) {
            errorMessage = "Person not found!";
        } else {
            found = true;
        }
        return new PersonResponse(personFound, found, errorMessage);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (username.equals("admin") && password.equals("12345")) {
            return "redirect:/verification";
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }

    // Define the Person class which represents each record in the JSON file
    public static class Person {
        public int id;
        public String name;
        public int barcodeId;
        public String city;
        public String branch;

        public Person(int id, String name, int barcodeId, String city, String branch) {
            this.id = id;
            this.name = name;
            this.barcodeId = barcodeId;
            this.city = city;
            this.branch = branch;
        }

        public static List<Person> getMockData() {
            return List.of(
                new Person(1670, "Akif Quddus Khan", 123456, "Ski", "Nordre Follo"),
                new Person(1843, "Maria Svensson", 234567, "Oslo", "Oslo Sentrum"),
                new Person(2052, "Jonas Bergström", 345678, "Bergen", "Bergen Vest"),
                new Person(2082, "Emma Hansen", 456789, "Trondheim", "Trondheim Øst"),
                new Person(2598, "Lars Andersen", 567890, "Stavanger", "Stavanger Nord")
            );
        }
    }

    public static class PersonResponse {
        private Person person;
        private boolean found;
        private String errorMessage;

        public PersonResponse(Person person, boolean found, String errorMessage) {
            this.person = person;
            this.found = found;
            this.errorMessage = errorMessage;
        }

        public Person getPerson() {
            return person;
        }

        public boolean isFound() {
            return found;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
