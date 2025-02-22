package com.osiris.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends AbstractEntity{

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean isActive;
    private Long userId;

    @OneToOne
    private Role role;

    @OneToOne
    private Adress adress;

    @OneToOne
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Contact> contact;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;
}
