package com.bookStore.rest.resourse.asm;

import com.bookStore.core.services.util.BookList;
import com.bookStore.rest.mvc.BookProfileController;
import com.bookStore.rest.resourse.BookListResourse;
import com.bookStore.rest.resourse.BookProfileResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/9/2017.
 */
public class BookListResourceAsm  extends ResourceAssemblerSupport<BookList,BookListResourse> {
    public BookListResourceAsm() {
        super(BookProfileController.class, BookListResourse.class);
    }

    @Override
    public BookListResourse toResource(BookList bookList) {
        List<BookProfileResource> resourses = new BookProfileResourceAsm().toResources(bookList.getBooks());
        BookListResourse listResourse= new BookListResourse();
        listResourse.setBooks(resourses);
        listResourse.add(linkTo(methodOn(BookProfileController.class).findAllBooks()).withSelfRel());
        return listResourse;

    }
}
