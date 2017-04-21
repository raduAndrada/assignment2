package com.bookStore.core.repositories;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.services.util.RegularUserList;

/**
 * Created by Andrada on 4/10/2017.
 */
public interface RegularUserRepo {
    public RegularUser createRegularUser(RegularUser newUser);
    public RegularUser  deleteRegularUser(Long id);
    public RegularUser updateRegularUser(Long id, RegularUser newUser);
    public RegularUser findUserById(Long id);
    public RegularUserList getAllUsers();
    public RegularUser getUserByUsername(String username);
}
