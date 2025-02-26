package com.osiris.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends AbstractEntity implements UserDetails {

    private String firstname;
    private String lastname;
    @Column(unique = true)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
