package com.springclass.unit;

import com.springclass.configuration.JavaConfig;
import com.springclass.domain.DVDDetails;
import com.springclass.domain.DVDInfo;
import com.springclass.domain.InvalidDvdIdException;

import com.springclass.presentation.AddDVDController;
import com.springclass.service.InventoryManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.MockitoAnnotations.initMocks;

import static com.springclass.presentation.Views.*;

//@RunWith(Parameterized.class)
//@ContextConfiguration(classes = JavaConfig.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class AddDVDControllerTest {

    @InjectMocks
    private AddDVDController controller;

    @Mock
    private InventoryManager manager;

    private DVDDetails details;
    private DVDInfo info;

    //-----------------------------------------------------------------------//

    @Before
    public void beforeEachTest() {
        initMocks(this);

        details = new DVDDetails();
        details.setId("100");
        details.setReleaseYear("2006");
        details.setTitle("24 - Season Five (2005)");
        details.setActors("Kiefer Sutherland, Dennis Haysbert, James Badge Dale, William Devane, Alberta Watson");

        info = new DVDInfo();
        info.setId("100");
        info.setYearMonthRelease("2006");
        info.setTitle("24 - Season Five (2005)");
        info.setStarring("Kiefer Sutherland, Dennis Haysbert, James Badge Dale, William Devane, Alberta Watson");

    }

    //-----------------------------------------------------------------------//

    @Test
    public void add() throws Exception {

        /*doNothing().when(manager).addDvd(details);

        ModelAndView result = controller.add(details, null, null);

        verify(manager, atLeastOnce()).addDvd(details);
        assertThat(result.getViewName(), is(DETAIL));

        DVDInfo returned = (DVDInfo)result.getModel().get("dvd");
//        assertThat(returned.getId(), is("100"));*/
    }


    @Test
    public void add_Throw_InvalidDvdIdException() throws Exception {

        /*doThrow(new InvalidDvdIdException("add exception")).when(manager).addDvd(details);

        ModelAndView result = controller.add(details, null, null);

        verify(manager, atLeastOnce()).addDvd(details);
        assertThat(result.getViewName(), is(ADD_DVD));

        DVDDetails returned = (DVDDetails)result.getModel().get("command");
//        assertThat(returned.getId(), is("100"));*/
    }



    @Test
    public void showForm() throws Exception {

        ModelAndView result = controller.showForm();

       assertThat(result.getViewName(), is(ADD_DVD));

        DVDDetails returned = (DVDDetails)result.getModel().get("command");
        assertThat(returned.getId(), is(nullValue()));
    }


} // The End...
