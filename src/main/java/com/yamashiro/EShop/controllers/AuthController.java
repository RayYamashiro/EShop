package com.yamashiro.EShop.controllers;

import com.yamashiro.EShop.models.Person;
import com.yamashiro.EShop.services.RegistrationService;
import com.yamashiro.EShop.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";
        registrationService.register(person);

        return "redirect:/auth/login";
    }
}
