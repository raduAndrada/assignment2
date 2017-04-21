package com.bookStore.core.security;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.services.RegularUserService;
import com.bookStore.core.services.impl.RegularUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Andrada on 4/17/2017.
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private RegularUserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegularUser user = service.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(user.toString());
        }
        return new AccountUserDetails(user);
    }
}
