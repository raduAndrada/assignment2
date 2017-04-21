package com.bookStore.rest.resourse;

import com.bookStore.core.models.entities.BookProfile;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/9/2017.
 */
public class BookListResourse extends ResourceSupport {

    private List<BookProfileResource> books = new ArrayList<BookProfileResource>();

    public List<BookProfileResource> getBooks() {
        return books;
    }

    public void setBooks(List<BookProfileResource> books) {
        this.books = books;
    }


}
