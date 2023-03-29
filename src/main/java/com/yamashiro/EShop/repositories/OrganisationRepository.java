package com.yamashiro.EShop.repositories;

import com.yamashiro.EShop.models.Organisation;
import com.yamashiro.EShop.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
    Optional<Organisation> findByName(String name);
}
