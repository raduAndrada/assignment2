package com.bookStore.core.repositories.jpa;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.repositories.RegularUserRepo;
import com.bookStore.core.repositories.parsers.RegularUserParser;
import com.bookStore.core.services.util.RegularUserList;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Andrada on 4/12/2017.
 */
public class RegularUserRepoImpl implements RegularUserRepo{
    public RegularUserRepoImpl() {
        try {
            users = parser.readUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RegularUser createRegularUser(RegularUser newUser) {
        if(users!= null) {
            users.add(newUser);
            Long id =generateUniqueId();
            newUser.setId(id);
            try {
                parser.setUsers(users);
                parser.writeUsers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {users = new RegularUserList();
        users.setUsers(new ArrayList<RegularUser>());
        users.add(newUser);
        newUser.setId(0L);}
        return newUser;

    }

    private RegularUserParser parser= new RegularUserParser();
    private RegularUserList users;



    @Override
    public RegularUser deleteRegularUser(Long id) {
        RegularUser usr = users.find(id);
         users.deleteUser(id);
        try {
            parser.setUsers(users);
            parser.writeUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usr;
    }

    @Override
    public RegularUser updateRegularUser(Long id, RegularUser newUser) {
        users.udpate(id, newUser);
        try {
            parser.setUsers(users);
            parser.writeUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newUser;
    }

    @Override
    public RegularUser findUserById(Long id) {
        return users.find(id);
    }

    @Override
    public RegularUserList getAllUsers()  {
        try {
            users = parser.readUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public RegularUser getUserByUsername(String username) {
        for(RegularUser r : users.getUsers())
        {
            if (r.getUsername().equals(username)) return r;
        }
        return null;
    }


    private Long generateUniqueId()
    {
        long val = -1;
        do
        {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            val = bi.longValue();
        } while (val < 0);
        return val;
    }
}
