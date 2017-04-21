package com.bookStore.core.repositories.parsers;

import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.services.util.RegularUserList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Andrada on 4/10/2017.
 */
public class RegularUserParser {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("App.xml");
    private XMLConvertor convertor = (XMLConvertor) applicationContext.getBean("XMLConverter");
    private RegularUserList users;
    private static final String XML_FILE_NAME = "users.xml";
    public RegularUserParser() {
        this.applicationContext = new ClassPathXmlApplicationContext("App.xml");
        convertor = (XMLConvertor) applicationContext.getBean("XMLConverter");}

    public RegularUserList readUsers() throws IOException {
        users = new RegularUserList();
        users = (RegularUserList) convertor.convertFromXMLToObject(XML_FILE_NAME);
        return users;
    }

    public void writeUsers() throws Exception {
        convertor.convertFromObjectToXML(users,XML_FILE_NAME);
    }

    public RegularUserList getUsers() {
        return users;
    }

    public void setUsers(RegularUserList users) {
        this.users = users;
    }
}
