package com.yamashiro.EShop.services;

import com.yamashiro.EShop.models.Organisation;

import com.yamashiro.EShop.repositories.OrganisationRepository;
import com.yamashiro.EShop.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationForOrganisation {
    private final OrganisationRepository organisationRepository;

    @Autowired
    public RegistrationForOrganisation(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }


    @Transactional
    public void register(Organisation organisation)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        organisation.setId(personDetails.getPerson().getId());
        organisationRepository.save(organisation);
    }
}
