package com.bookStore.core.services.util;

import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.rest.resourse.BookProfileResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrada on 4/9/2017.
 */
public class BookList {
    private List<BookProfile> books = new ArrayList<BookProfile>();
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookProfile> getBooks() {
        return books;
    }

    public void setBooks(List<BookProfile> books) {
        this.books = books;
    }



    public boolean add(BookProfile b) {
        boolean found = false;
        for(BookProfile bk:books) {
            if (bk.getId() == b.getId()) found = true;
        }
        if (!found) books.add(b);
        return  found;
    }

    public List<BookProfile> deleteBookProfile(Long id)
    {
        BookProfile aux = new BookProfile();
        for(BookProfile bk:books) {
            if (bk.getId().equals(id)) {
                aux = bk;
            }
        }
        this.books.remove(aux);
        return  books;
    }
    public BookProfile find (Long id) {
        for(BookProfile bk:books) {
            if (bk.getId().equals(id)) return bk;
        }
        return  null;
    }

    public  BookProfile udpate(Long id, BookProfile newBook)
    {
        BookProfile aux = new BookProfile();
        for(BookProfile bk:books) {
            if (bk.getId().equals(id))
            {
                bk.setTitle(newBook.getTitle());
                bk.setAuthor(newBook.getAuthor());
                bk.setGenre(newBook.getGenre());
                bk.setPrice(newBook.getPrice());
                bk.setQuantity(newBook.getQuantity());
                aux = bk;
            }
        }
        return  aux;
    }

    public BookList search(String book)
    {
        BookList aux= new BookList();
        for(BookProfile b : books)
        {
            if(b.getAuthor().contains(book) || b.getGenre().equals(book) || b.getTitle().contains(book) || book.contains(b.getTitle())) aux.add(b);
        }
        return aux;
    }

}
