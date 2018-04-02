package com.springclass.unit;

import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.fixture.DVDInfoFixture;
import com.springclass.presentation.ViewDVDController;
import com.springclass.service.InventoryManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

import static com.springclass.presentation.Views.DETAIL;
import static com.springclass.presentation.Views.LIST_ALL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

//@RunWith(Parameterized.class)
//@ContextConfiguration(classes = JavaConfig.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class ViewDVDControllerTest {

    @InjectMocks
    private ViewDVDController controller;

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

        Collection<DVDInfo> allTitles = fixture.getTitles();

        when(manager.findAll()).thenReturn(allTitles);

        ModelAndView result = controller.viewAll();

//        verify(manager, times(1)).findAll();
//        assertThat(result.getViewName(), is(LIST_ALL));

        Collection<DVDInfo> returned = (Collection<DVDInfo>)result.getModel().get("catalog");
//        assertThat(returned.size(), is(15));
    }



    @Test
    public void details() throws Exception {

        when(manager.getDetails("100")).thenReturn(details);
        //TODO
        //ModelAndView result = controller.details("100");

//        verify(manager, times(1)).getDetails("100");
//        assertThat(result.getViewName(), is(DETAIL));
        //TODO
         // DVDDetails returned = (DVDDetails)result.getModel().get("dvd");
//        assertThat(returned.getId(), is("100"));
    }


}
