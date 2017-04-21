package com.bookStore.rest.mvc;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.services.RegularUserService;
import com.bookStore.core.services.exceptions.AccountExistsException;
import com.bookStore.core.services.util.RegularUserList;
import com.bookStore.rest.exceptions.ConflictException;
import com.bookStore.rest.exceptions.ForbiddenException;
import com.bookStore.rest.resourse.RegularUserListResource;
import com.bookStore.rest.resourse.RegularUserResource;
import com.bookStore.rest.resourse.asm.RegularUserListAsm;
import com.bookStore.rest.resourse.asm.RegularUserResourceAsm;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.Arrays;

/**
 * Created by Andrada on 4/9/2017.
 */
@Controller
@RequestMapping("/rest/regular-users")
@PreAuthorize("permitAll")
public class RegularUserController {

    @Autowired
    private RegularUserService regularUserService;

    public RegularUserController(RegularUserService regularUserService)
    {
        this.regularUserService =regularUserService;
    }

    @RequestMapping(method= RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<RegularUserResource> sentRegularUser(
            @RequestBody RegularUserResource sentRegularUser
    ){
    try
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            RegularUser loggedIn = regularUserService.getUserByUsername(details.getUsername());

            if(loggedIn.isAdmin()) {
                RegularUser createdUser = regularUserService.createRegularUser(sentRegularUser.toRegularUser());
                RegularUserResource res = new RegularUserResourceAsm().toResource(createdUser);
                HttpHeaders headers = new HttpHeaders();

                headers.setLocation(URI.create(res.getLink("self").getHref()));

                return new ResponseEntity<RegularUserResource>(res, headers, HttpStatus.CREATED);
            }else throw new ForbiddenException();
        }
        else throw new ForbiddenException();
    }catch(AccountExistsException ex)
    {
        throw new ConflictException(ex);
    }
    }


    @RequestMapping(value="/{accountId}",method =RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<RegularUserResource> getRegularUser(@PathVariable Long accountId)
    {
        RegularUser user = regularUserService.findUserById(accountId);
        if(user !=null)
        {
            RegularUserResource res = new RegularUserResourceAsm().toResource(user);
            return new ResponseEntity<RegularUserResource>(res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<RegularUserResource>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/delete",method= RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<RegularUserResource> deleteRegularUser(@RequestBody RegularUserResource sentRegularUser)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            RegularUser loggedIn = regularUserService.getUserByUsername(details.getUsername());

            if(loggedIn.isAdmin()) {
                RegularUser user = regularUserService.deleteRegularUser(sentRegularUser.getRid());
                if (user != null) {
                    if(user.isAdmin()) throw new ForbiddenException();
                    RegularUserResource res = new RegularUserResourceAsm().toResource(user);
                    return new ResponseEntity<RegularUserResource>(res, HttpStatus.OK);
                } else {
                    return new ResponseEntity<RegularUserResource>(HttpStatus.NOT_FOUND);
                }
            }
            else throw new ForbiddenException();
        }
        else throw new ForbiddenException();
    }
    @RequestMapping(value="/update",method= RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<RegularUserResource> updateRegularUser( @RequestBody RegularUserResource sentRegularUser)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            RegularUser loggedIn = regularUserService.getUserByUsername(details.getUsername());
            if(loggedIn.isAdmin()) {
                RegularUser user = regularUserService.updateRegularUser(sentRegularUser.getRid(), sentRegularUser.toRegularUser());
                if (user != null) {
                    RegularUserResource res = new RegularUserResourceAsm().toResource(user);
                    return new ResponseEntity<RegularUserResource>(res, HttpStatus.OK);
                } else {
                    return new ResponseEntity<RegularUserResource>(HttpStatus.NOT_FOUND);
                }
            }
            else throw new ForbiddenException();
        }
        else throw new ForbiddenException();
    }

    @RequestMapping(value="/users", method= RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<RegularUserListResource> findAllUsers() {
        RegularUserList users = regularUserService.getAllUsers();
        RegularUserListResource res = new RegularUserListAsm().toResource(users);
        return new ResponseEntity<RegularUserListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<RegularUserListResource> findUsersByUsernameAndPassword(@RequestParam(value="username", required = false) String username, @RequestParam(value="password", required = false) String password)
    {
        RegularUserList users = regularUserService.getAllUsers();
        RegularUser usr = regularUserService.getUserByUsername(username);
        if (usr != null)
        {
            if(password != null) {
                if(usr.getPassword().equals(password)) users = new RegularUserList(Arrays.asList(usr));
            }else{
                users = new RegularUserList(Arrays.asList(usr));
            }
        }

        RegularUserListResource res = new RegularUserListAsm().toResource(users);
        return new ResponseEntity<RegularUserListResource> (res,HttpStatus.OK);
    }

}
