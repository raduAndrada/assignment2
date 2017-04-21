package com.bookStore.rest.resourse;

import com.bookStore.core.models.entities.BookProfile;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Andrada on 4/9/2017.
 */
public class BookProfileResource extends ResourceSupport{
    private String title;
    private int quantity;
    private float price;
    private String genre;
    private String author;
    private Long rid;


    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookProfile toBookProfile()
    {
        BookProfile book = new BookProfile();
        book.setAuthor(author);
        book.setGenre(genre);
        book.setPrice(price);
        book.setQuantity(quantity);
        book.setTitle(title);
        return book;

    }
}

