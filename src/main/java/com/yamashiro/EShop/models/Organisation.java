package com.yamashiro.EShop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name ="organisation")
public class Organisation {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person owner;

    @OneToMany(mappedBy = "orgOwner")
    private List<Product> products;

    @NotEmpty(message = "description should not be empty")
    @Column(name="description")
    private String description;

    @NotEmpty(message = "name should not be empty")
    @Column(name="name")
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private OrganisationStatus organisationStatus;
    public Organisation(Person owner, String description , String name) {
        this.owner = owner;
        this.description = description;
        this.name=name;
        this.organisationStatus = OrganisationStatus.ON_VERIFICATION;
    }


    public Organisation(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //@Column(name="logo")
    //private Image image;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganisationStatus getStatus() {
        return organisationStatus;
    }

    public void setStatus(OrganisationStatus organisationStatus) {
        this.organisationStatus = organisationStatus;
    }
}

