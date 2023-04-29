package com.yamashiro.EShop.services;

import com.yamashiro.EShop.models.Organisation;
import com.yamashiro.EShop.models.Person;
import com.yamashiro.EShop.repositories.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganisationDetailService {

    private final OrganisationRepository organisationRepository;
@Autowired
    public OrganisationDetailService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public Optional<Organisation> findOrgByName(String name)
    {
        Optional<Organisation> organisation = organisationRepository.findByName(name);
        return organisation;
    }

    public Optional<Organisation> findByUserId(Person owner)
    {
        Optional<Organisation> optional = organisationRepository.findByOwner(owner);
        return optional;
    }

}
