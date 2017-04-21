package com.bookStore.rest.mvc;

import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.services.BookProfileService;
import com.bookStore.core.services.exceptions.BookExistsException;
import com.bookStore.core.services.util.BookList;
import com.bookStore.rest.exceptions.ConflictException;
import com.bookStore.rest.resourse.BookListResourse;
import com.bookStore.rest.resourse.BookProfileResource;
import com.bookStore.rest.resourse.asm.BookListResourceAsm;
import com.bookStore.rest.resourse.asm.BookProfileResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;

/**
 * Created by Andrada on 4/9/2017.
 *
 */

@Controller
@RequestMapping("/rest/book-entries")
public class BookProfileController {

    @Autowired
    private BookProfileService service;
    public BookProfileController (BookProfileService service)
    {
        this.service = service;
    }

    @RequestMapping(method= RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<BookProfileResource> sentBook(
            @RequestBody BookProfileResource sentBook
    ){
        try
        {
            BookProfile createdBook = service.createBookProfile(sentBook.toBookProfile());
            BookProfileResource res = new BookProfileResourceAsm().toResource(createdBook);
            HttpHeaders headers =new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<BookProfileResource>(res,headers, HttpStatus.CREATED);
        }catch(BookExistsException ex)
        {
            throw new ConflictException(ex);
        }
    }


    @RequestMapping(value="/{bookProfileId}",method=RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<BookProfileResource> getBookProfile(@PathVariable Long bookProfileId)
    {
        BookProfile book = service.findBookById(bookProfileId);
        if(book !=null)
        {
            BookProfileResource res = new BookProfileResourceAsm().toResource(book);
            return new ResponseEntity<BookProfileResource> (res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BookProfileResource> (HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/delete",method= RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<BookProfileResource> deleteBook(@RequestBody BookProfileResource sentBook)
    {
        BookProfile book = service.deleteBookProfile(sentBook.getRid());
        if(book !=null)
        {
            BookProfileResource res = new BookProfileResourceAsm().toResource(book);
            return new ResponseEntity<BookProfileResource> (res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BookProfileResource> (HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value="/update",method= RequestMethod.POST)
    public ResponseEntity<BookProfileResource>  updateBookProfile(@RequestBody BookProfileResource sentBook)
    {
        BookProfile book = service.updateBookProfile(sentBook.getRid(),sentBook.toBookProfile());
        if(book !=null)
        {
            BookProfileResource res = new BookProfileResourceAsm().toResource(book);
            return new ResponseEntity<BookProfileResource> (res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BookProfileResource> (HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value="/all-books",method= RequestMethod.GET)
    public ResponseEntity<BookListResourse>  findAllBooks()
    {
        BookList books = service.getAllBooks();
        BookListResourse res = new BookListResourceAsm().toResource(books);
        return new ResponseEntity<BookListResourse> (res,HttpStatus.OK);

    }

    @PreAuthorize("permitAll")
    @RequestMapping(value="/search",method= RequestMethod.POST)
    public ResponseEntity<BookListResourse>  searchBookProfile(@RequestBody String search)
    {
        BookList books = service.searchForBook(search);
        if(books !=null)
        {
            BookListResourse res = new BookListResourceAsm().toResource(books);
            return new ResponseEntity<BookListResourse> (res,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<BookListResourse> (HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/download/{filename}/{format}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable String filename,@PathVariable String format) throws Exception {


        File file = new File (filename+"."+format);
        try (InputStream fileInputStream = new FileInputStream(file);) {

            byte[] contents = IOUtils.toByteArray(fileInputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/"+ format));
            String finalFilename = filename+"."+format;
            headers.setContentDispositionFormData(finalFilename, finalFilename);
            ResponseEntity<byte[]> response2 = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
            fileInputStream.close();
            return response2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
