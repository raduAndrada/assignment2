package com.bookStore.rest.resourse.asm;

import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.rest.mvc.BookProfileController;
import com.bookStore.rest.resourse.BookProfileResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Andrada on 4/9/2017.
 */
public class BookProfileResourceAsm extends ResourceAssemblerSupport<BookProfile,BookProfileResource> {
    public BookProfileResourceAsm() {
        super(BookProfileController.class, BookProfileResource.class);
    }

    @Override
    public BookProfileResource toResource(BookProfile bookProfile) {
        BookProfileResource res = new BookProfileResource();
        res.setAuthor(bookProfile.getAuthor());
        res.setGenre(bookProfile.getGenre());
        res.setPrice(bookProfile.getPrice());
        res.setQuantity(bookProfile.getQuantity());
        res.setTitle(bookProfile.getTitle());
        res.setRid(bookProfile.getId());
        Link self =linkTo(methodOn(BookProfileController.class).getBookProfile((bookProfile.getId()))).withSelfRel();

        res.add(self);
        return res;
    }
}
