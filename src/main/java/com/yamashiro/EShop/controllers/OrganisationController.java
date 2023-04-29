package com.yamashiro.EShop.controllers;

import com.yamashiro.EShop.models.Organisation;

import com.yamashiro.EShop.models.OrganisationStatus;
import com.yamashiro.EShop.repositories.OrganisationRepository;
import com.yamashiro.EShop.security.PersonDetails;
import com.yamashiro.EShop.services.OrganisationDetailService;
import com.yamashiro.EShop.services.RegistrationForOrganisation;
import com.yamashiro.EShop.util.OrganisationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/organisation")
public class OrganisationController {
    private final OrganisationValidator organisationValidator;
    private final RegistrationForOrganisation registration;
    private final OrganisationDetailService organisationDetailService;
    @Autowired
    public OrganisationController(OrganisationValidator organisationValidator, RegistrationForOrganisation registration, OrganisationDetailService organisationDetailService) {
        this.organisationValidator = organisationValidator;
        this.registration = registration;
        this.organisationDetailService = organisationDetailService;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        organisation.setOwner(personDetails.getPerson());
        registration.register(organisation);
        return "redirect:/hello";
    }

    @GetMapping("/show")
    public String showActiveOrganisation(@ModelAttribute("organisation") Optional<Organisation> organisation , @ModelAttribute("list") ArrayList<Organisation> arrayList) //TODO
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Optional<Organisation> optional = organisationDetailService.findByUserId(personDetails.getPerson());
        if(organisation.isPresent())
        {
            arrayList = new ArrayList<>(optional.stream().filter((organisation1 -> organisation1.getStatus().equals(OrganisationStatus.ACTIVE))).toList());
        }
        //else
        //    arrayList = new ArrayList<>();
        return "organisation/show";
    }
}
