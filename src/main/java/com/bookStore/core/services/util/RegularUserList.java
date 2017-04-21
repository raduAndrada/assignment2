package com.bookStore.core.services.util;

import com.bookStore.core.models.entities.RegularUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/9/2017.
 */
public class RegularUserList {
    private List<RegularUser> users = new ArrayList<RegularUser>();
    private  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RegularUser> getUsers() {
        return users;
    }

    public void setUsers(List<RegularUser> users) {
        this.users = users;
    }



    public boolean add(RegularUser b) {
        boolean found = false;
        for(RegularUser us:users) {
            if (us.getId() == b.getId()) found = true;
        }
        if (!found) users.add(b);
        return  found;
    }

    public List<RegularUser> deleteUser(Long id)
    {

        RegularUser aux = new RegularUser();
        for(RegularUser us:users) {
            if ( us.getId().equals(id)) {
                aux = us;
            }
        }
       if(users.size()> 0) users.remove(aux);
        System.out.print(users.size());
        return  users;
    }

    public RegularUserList() {
    }

    public RegularUserList(List<RegularUser> users) {

        this.users = users;
    }

    public RegularUser find (Long id) {
        RegularUser aux = null;
        for(RegularUser reg:users) {
            System.out.println(reg.getId());

            if (reg.getId().equals(id))
                aux =reg;
        }
        return aux;
    }

    public  RegularUser udpate(Long id, RegularUser newBook)
    {
        RegularUser aux = new RegularUser();
        for(RegularUser reg:users) {
            if (reg.getId().equals(id))
            {
                reg.setAddress(newBook.getAddress());
                reg.setSurname(newBook.getSurname());
                reg.setEmail(newBook.getEmail());
                reg.setPassword(newBook.getPassword());
                reg.setPhoneNumber(newBook.getPhoneNumber());
                reg.setUsername(newBook.getUsername());
                aux = reg;
            }
        }
        return  aux;
    }

    @Override
    public String toString() {
        return "RegularUserList{" +
                "users=" + users +
                ", id=" + id +
                '}';
    }
}
