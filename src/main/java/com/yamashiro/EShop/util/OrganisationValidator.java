package com.yamashiro.EShop.util;

import com.yamashiro.EShop.models.Organisation;

import com.yamashiro.EShop.services.OrganisationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
@Component
public class OrganisationValidator implements Validator {
    private final OrganisationDetailService organisationDetailService;
@Autowired
    public OrganisationValidator(OrganisationDetailService organisationDetailService) {
        this.organisationDetailService = organisationDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Organisation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Organisation organisation = (Organisation) target;
        Optional<Organisation> org = organisationDetailService.findOrgByName(organisation.getName());
        if(org.isPresent())
            errors.rejectValue("name" , "" , "Организация с таким именем существует");
        else
            return;
    }
}
