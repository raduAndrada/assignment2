package com.bookStore.rest.resourse;

import com.bookStore.core.models.entities.RegularUser;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/9/2017.
 */
public class RegularUserListResource extends ResourceSupport{
    private List<RegularUserResource> users = new ArrayList<RegularUserResource>();

    public List<RegularUserResource> getUsers() {
        return users;
    }

    public void setUsers(List<RegularUserResource> users) {
        this.users = users;
    }
}
