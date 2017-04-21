import com.bookStore.core.models.entities.RegularUser;
import com.bookStore.core.services.RegularUserService;
import com.bookStore.core.services.util.RegularUserList;
import com.bookStore.rest.mvc.RegularUserController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Andrada on 4/9/2017.
 */
public class RegularUserControllerTest {

    @InjectMocks
    private RegularUserController controller;

    @Mock
    private RegularUserService service;

    private MockMvc mockMvc;


    private ArgumentCaptor<RegularUser> userCaptor;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        userCaptor = ArgumentCaptor.forClass(RegularUser.class);

    }

    @Test
    public void getExistingUserTest() throws Exception
    {
        RegularUser entry = new RegularUser();

        entry.setId(1L);
        entry.setName("test name");
        entry.setPassword("test");

        when(service.findUserById(1L)).thenReturn(entry);
        mockMvc.perform(get("/rest/regular-users/1"))
                .andDo(print())
                .andExpect(jsonPath("$.name",is(entry.getName())))
                .andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/regular-users/1"))))
                .andExpect(status().isOk())
        ;
    }


    @Test
    public void findAllUsersTest () throws Exception {

        RegularUser entryA = new RegularUser ();

        entryA.setId(1L);
        entryA.setName("Test Title");

        RegularUser entryB = new RegularUser ();

        entryB.setId(2L);
        entryB.setName("Test Title");

        List<RegularUser> usersList = new ArrayList();
        usersList.add(entryA);
        usersList.add(entryB);

        RegularUserList list = new RegularUserList();
        list.setUsers(usersList);
        list.setId(1L);

        when(service.getAllUsers()).thenReturn(list);

        mockMvc.perform(get("/rest/regular-users"))
                .andDo(print())
                .andExpect(jsonPath("$.users[*].name", hasItem(is("Test Title"))))
                .andExpect(status().isOk());
    }


}
