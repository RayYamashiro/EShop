package com.yamashiro.EShop.services;

import com.yamashiro.EShop.models.Organisation;

import com.yamashiro.EShop.models.OrganisationStatus;
import com.yamashiro.EShop.repositories.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        organisation.setStatus(OrganisationStatus.ON_VERIFICATION);
        organisationRepository.save(organisation);
    }
}
