package com.yamashiro.EShop.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name ="product")
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name="Name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organisation orgOwner;
}
