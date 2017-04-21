package com.bookStore.core.repositories.parsers;

import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.core.services.util.BookList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Andrada on 4/10/2017.
 */
public class BookProfileParser {
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("App.xml");
    private XMLConvertor convertor = (XMLConvertor) applicationContext.getBean("XMLConverter");
    private BookList books;
    private static final String XML_FILE_NAME = "books.xml";
    public BookProfileParser() {
        this.applicationContext = new ClassPathXmlApplicationContext("App.xml");
        convertor = (XMLConvertor) applicationContext.getBean("XMLConverter");}

    public BookList readBookProfile() throws IOException {
        books = new BookList();
        books = (BookList) convertor.convertFromXMLToObject(XML_FILE_NAME);
        return books;
    }

    public void writeBookProfile() throws Exception {
        convertor.convertFromObjectToXML(books,XML_FILE_NAME);
    }

    public BookList getBooks() {
        return books;
    }

    public void setBooks(BookList books) {
        this.books = books;
    }
}
