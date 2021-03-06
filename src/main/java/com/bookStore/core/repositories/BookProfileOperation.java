package com.bookStore.core.repositories;

import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.core.services.util.BookList;

/**
 * Created by Andrada on 4/10/2017.
 */
public interface BookProfileOperation {
    public BookProfile createBookProfile(BookProfile book);
    public BookProfile deleteBookProfile(Long id);
    public BookProfile updateBookProfile(Long id, BookProfile newBook);
    public BookProfile findBookById(Long id);
    public BookList getAllBooks();
    public BookList searchForBook(String search);
}
