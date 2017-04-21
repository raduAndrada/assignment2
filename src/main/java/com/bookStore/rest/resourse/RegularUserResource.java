package com.bookStore.rest.resourse;

import com.bookStore.core.models.entities.RegularUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Andrada on 4/9/2017.
 */
public class RegularUserResource extends ResourceSupport{
    private String username;
    private String password;
    private String name;
    private Long rid;
    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String surname;
    private String Address;
    private String email;
    private Long phoneNumber;

    public RegularUser toRegularUser()
    {
        RegularUser reg = new RegularUser();
        reg.setAddress(Address);
        reg.setEmail(email);
        reg.setName(name);
        reg.setPassword(password);
        reg.setPhoneNumber(phoneNumber);
        reg.setSurname(surname);
        reg.setUsername(username);
        reg.setAdmin(admin);

        return reg;
    }

}
