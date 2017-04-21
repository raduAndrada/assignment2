package com.bookStore.rest.resourse.asm;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.rest.mvc.RegularUserController;
import com.bookStore.rest.resourse.RegularUserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Andrada on 4/9/2017.
 */
public class RegularUserResourceAsm extends ResourceAssemblerSupport<RegularUser,RegularUserResource>{

    public RegularUserResourceAsm( ){
        super(RegularUserController.class,RegularUserResource.class);
    }

    @Override
    public RegularUserResource toResource(RegularUser regularUser) {
        RegularUserResource res = new RegularUserResource();
        res.setName(regularUser.getName());
        res.setSurname(regularUser.getSurname());
        res.setEmail(regularUser.getEmail());
        res.setAddress(regularUser.getAddress());
        res.setUsername(regularUser.getUsername());
        res.setPassword(regularUser.getPassword());
        res.setPhoneNumber(regularUser.getPhoneNumber());
        res.setAdmin(regularUser.isAdmin());
        res.setRid(regularUser.getId());
        res.add(linkTo(methodOn(RegularUserController.class).getRegularUser(regularUser.getId())).withSelfRel());
        return res;
    }
}
