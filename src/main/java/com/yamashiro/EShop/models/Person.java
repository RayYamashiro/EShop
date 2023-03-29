package com.yamashiro.EShop.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name ="Person")
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty(message = "email  should not be empty")
    @Email(message = "email must be valid")
    @Column(name="email")
    private String email;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 2, max = 100 , message = "username must be between 2 and 100 digits")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private int balance;

    @OneToMany(mappedBy = "owner")
    private List<Organisation> organisations;


    public Person()
    {

    }
    public Person(String email, String username, String password,  List<Organisation> organisations) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.organisations = organisations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(List<Organisation> organisations) {
        this.organisations = organisations;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", organisations=" + organisations +
                '}';
    }
}
