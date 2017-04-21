package com.bookStore.core.services.impl;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.repositories.RegularUserRepo;
import com.bookStore.core.repositories.jpa.RegularUserRepoImpl;
import com.bookStore.core.services.RegularUserService;
import com.bookStore.core.services.util.RegularUserList;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrada on 4/10/2017.
 */
@Repository
public class RegularUserServiceImpl implements RegularUserService{

    RegularUserRepoImpl repo = new RegularUserRepoImpl();

    @Override
    public RegularUser createRegularUser(RegularUser newUser) {
        return repo.createRegularUser(newUser);
    }

    @Override
    public RegularUser deleteRegularUser(Long id) {
        return repo.deleteRegularUser(id);
    }


    @Override
    public RegularUser updateRegularUser(Long id, RegularUser newUser) {
        return repo.updateRegularUser(id, newUser);
    }

    @Override
    public RegularUser findUserById(Long id) {
        return repo.findUserById(id);
    }

    @Override
    public RegularUserList getAllUsers() {
        return repo.getAllUsers();
    }

    @Override
    public RegularUser getUserByUsername(String username) {
        return repo.getUserByUsername(username);
    }
}
