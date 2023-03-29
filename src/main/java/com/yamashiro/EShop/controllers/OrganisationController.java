package com.yamashiro.EShop.controllers;

import com.yamashiro.EShop.models.Organisation;

import com.yamashiro.EShop.services.RegistrationForOrganisation;
import com.yamashiro.EShop.util.OrganisationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/organisation")
public class OrganisationController {
    private final OrganisationValidator organisationValidator;
    private final RegistrationForOrganisation registration;
@Autowired
    public OrganisationController(OrganisationValidator organisationValidator, RegistrationForOrganisation registration) {
        this.organisationValidator = organisationValidator;
    this.registration = registration;
}

    @GetMapping("/neworg")
    public String newOrganisationPage(@ModelAttribute("organisation")Organisation organisation)
    {
        return "organisation/neworg";
    }

    @PostMapping("/neworg")
    public String create(@ModelAttribute("organisation") @Valid  Organisation organisation, BindingResult bindingResult){
        organisationValidator.validate(organisation, bindingResult);

        if (bindingResult.hasErrors())
            return "/organisation/neworg";




        registration.register(organisation);
        return "redirect:/hello";
    }

}
