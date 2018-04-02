package com.springclass.unit;

import com.springclass.domain.DVDDetails;
import com.springclass.fixture.DVDInfoFixture;
import com.springclass.service.InventoryManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

//@RunWith(Parameterized.class)
//@ContextConfiguration(classes = JavaConfig.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class ViewDVDControllerTest {

//    @InjectMocks
//    private ViewDVDController controller;

    @Mock
    private InventoryManager manager;

    private DVDDetails details;

    private DVDInfoFixture fixture = new DVDInfoFixture();

    @Before
    public void beforeEachTest() {
        initMocks(this);

        details = new DVDDetails();
        details.setId("100");
        details.setReleaseYear("2006");
        details.setTitle("24 - Season Five (2005)");
        details.setActors("Kiefer Sutherland, Dennis Haysbert, James Badge Dale, William Devane, Alberta Watson");

    }


    @Test
    public void viewAll() throws Exception {
    }

} // The End...
