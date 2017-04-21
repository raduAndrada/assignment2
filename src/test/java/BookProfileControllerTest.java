import com.bookStore.core.models.entities.BookProfile;
import com.bookStore.core.services.BookProfileService;
import com.bookStore.core.services.util.BookList;
import com.bookStore.rest.mvc.BookProfileController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Andrada on 4/9/2017.
 */
public class BookProfileControllerTest {
    @InjectMocks
    private BookProfileController controller;

    @Mock
    private BookProfileService service;

    private MockMvc mockMvc;


    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }

    @Test
    public void getExistingBookProfileTest() throws Exception
    {
        BookProfile entry = new BookProfile();

        entry.setId(1L);
        entry.setTitle("test title");

        when(service.findBookById(1L)).thenReturn(entry);
        mockMvc.perform(get("/rest/book-entries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title",is(entry.getTitle())))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/book-entries/1"))))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void createBookProfileTest() throws  Exception{
        BookProfile entry = new BookProfile();

        entry.setId(1L);
        entry.setTitle("test title");

        when(service.createBookProfile(any(BookProfile.class))).thenReturn(entry);
        mockMvc.perform(post("/rest/book-entries")
                .content("{\"name\":\"test\",\"title\":\"test\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location",org.hamcrest.Matchers.endsWith("/book-entries/1")))
                .andExpect(jsonPath("$.title",is(entry.getTitle())))
                .andExpect(status().isCreated());

    }



    @Test
    public void findAllBooksTest () throws Exception {

        BookProfile entryA = new BookProfile();

        entryA.setId(1L);
        entryA.setTitle("Test Title");

        BookProfile entryB = new BookProfile();

        entryB.setId(2L);
        entryB.setTitle("Test Title");

        List<BookProfile> bookList = new ArrayList();
        bookList.add(entryA);
        bookList.add(entryB);

       BookList list = new BookList();
        list.setBooks(bookList);
        list.setId(1L);

        when(service.getAllBooks()).thenReturn(list);

        mockMvc.perform(get("/rest/book-entries/all-books"))
                .andDo(print())
                .andExpect(jsonPath("$.books[*].title", hasItem(is("Test Title"))))
                .andExpect(status().isOk());
    }


}
