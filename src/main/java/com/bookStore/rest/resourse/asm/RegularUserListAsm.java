package com.bookStore.rest.resourse.asm;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.services.util.RegularUserList;
import com.bookStore.rest.mvc.RegularUserController;
import com.bookStore.rest.resourse.RegularUserListResource;
import com.bookStore.rest.resourse.RegularUserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/9/2017.
 */
public class RegularUserListAsm extends ResourceAssemblerSupport<RegularUserList,RegularUserListResource> {
    public RegularUserListAsm() {
        super(RegularUserController.class, RegularUserListResource.class);
    }

    @Override
    public RegularUserListResource toResource(RegularUserList regularUserList) {
        try {
            List<RegularUserResource> resourceList = new RegularUserResourceAsm().toResources(regularUserList.getUsers());
            RegularUserListResource resource = new RegularUserListResource();
            resource.setUsers(resourceList);
            resource.add(linkTo(methodOn(RegularUserController.class).findAllUsers()).withSelfRel());
            return resource;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }


}
