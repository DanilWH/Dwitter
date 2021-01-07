package com.example.Quoter.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="usr") // a single user will be stored in the table named "usr".
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Username can't be empty")
    private String username;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @Transient
    @NotBlank(message = "Password confirmation can't be empty")
    private String confirmPassword;
    private boolean active;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email can't be empty")
    private String email;
    private String activationCode;
    
    // allows to shape an additional table for storing an enum.
    @ElementCollection(targetClass=Role.class, fetch=FetchType.EAGER)
    // this allows us to create the table "user_role" for roles set that is joined with
    // the current table ("usr" table) using user_id.
    @CollectionTable(name="user_role", joinColumns=@JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING) // we want the enum is stored as a string.
    private Set<Role> roles;
    
    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public Set<Role> getRoles() {
        return this.roles;
    }

    public String getEmail() {
        return email;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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
        return isActive();
    }
}
