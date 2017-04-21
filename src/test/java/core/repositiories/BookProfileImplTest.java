package core.repositiories;

import com.bookStore.core.repositories.jpa.BookProfileOperationImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Andrada on 4/10/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:business-config.xml")
public class BookProfileImplTest {

    @Autowired
    private BookProfileOperationImpl repo;

    @Before
    public void setup()
    {

    }

    @Test
    public void test()
    {

    }

}
