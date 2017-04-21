package com.bookStore.core.services.impl;

import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.core.repositories.BookProfileOperation;
import com.bookStore.core.repositories.jpa.BookProfileOperationImpl;
import com.bookStore.core.services.BookProfileService;
import com.bookStore.core.services.util.BookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrada on 4/10/2017.
 */
@Repository
public class BookProfileServiceImpl implements BookProfileService{

    private BookProfileOperationImpl bookProfileOperation= new BookProfileOperationImpl();


    @Override
    public BookProfile createBookProfile(BookProfile book) {
        return bookProfileOperation.createBookProfile(book);
    }

    @Override
    public BookProfile deleteBookProfile(Long id) {
        return bookProfileOperation.deleteBookProfile(id);
    }

    @Override
    public BookProfile updateBookProfile(Long id, BookProfile newBook) {
        return bookProfileOperation.updateBookProfile(id,newBook);
    }

    @Override
    public BookProfile findBookById(Long id) {
        return bookProfileOperation.findBookById(id);
    }

    @Override
    public BookList getAllBooks() {
        return bookProfileOperation.getAllBooks();
    }

    @Override
    public BookList searchForBook(String search) {
        return bookProfileOperation.searchForBook(search);
    }
}
