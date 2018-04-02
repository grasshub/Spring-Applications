package com.springclass.bo;

import com.springclass.aop.utils.AOPUtils;
import com.springclass.configuration.JavaConfig;
import com.springclass.dao.*;
import com.springclass.domain.DVDInfo;

import com.springclass.domain.MemberException;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.*;


@ContextConfiguration(classes = JavaConfig.class)
//@ContextConfiguration("classpath*:applicationContext-kiosk.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class KioskServiceTests {

    private static final Logger logger = LoggerFactory
            .getLogger(KioskServiceTests.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("kioskServiceUS")
    private KioskService kioskServiceUS;

    @Autowired
    @Qualifier("kioskServiceNL")
    private KioskService kioskServiceNL;

    @Mock
    private DVDInfoDAO dvdInfoDAO;

    @Mock
    private MemberDAO memberDAO;


    @Autowired
    private AirportLocationDAO airportLocationDAO;
    @Autowired
    private DVDLocationDAO dvdLocationDAO;
    @Autowired
    private LoanDAO loanDAO;



    @Value("${location.id.us}")
    private String locationUS;
    @Value("${location.id.nl}")
    private String locationNL;
    @Value("${location.id.fr}")
    private String locationFR;


    @Before
    public void beforeEachTest() throws MemberException {
        MockitoAnnotations.initMocks(this);

        when(memberDAO.getMemberID(anyString(), anyString()))
                .thenReturn(null);
    }

    @After
    public void afterEachTest(){
    }


    @Test
    public void test__search_by_title() {

        String searchTitle = "2 Fast 2 Furious (Widescreen Edition)";
        String username = "j.wirth";
        String password = "whoknows";

        String locationId = locationNL;


        //Create Mock for dvdInfoDAO
//        dvdInfoDAO = new DVDInfoDAOImpl();
//        ((KioskServiceImpl) kioskServiceUS).setDvdInfoDAO(dvdInfoDAO);

        List<DVDInfo> result = kioskServiceUS.searchByTitle(searchTitle);

        // TODO: set to > 0
        assertThat(result.size(), is(1));

        // Java assert's are available:
        assert result.size() == 1;

        DVDInfo dvdInfo = result.get(0);

        int loanID = kioskServiceUS.loanDVD(dvdInfo.getId(), username, password, new Date(), locationId);
        logger.info("Loan added underNumber: " + loanID);

        assertThat(loanID, is(9494));

        // end lab here
    }



    @Test
    public void test__search_by_title_throws_MemberException()
    throws MemberException{

        String searchTitle = "2 Fast 2 Furious (Widescreen Edition)";
        String username = "foo";
        String password = "bar";
        String locationId = locationUS;

        when(memberDAO.getMemberID(anyString(), anyString()))
                .thenThrow(new MemberException("mocked MemberException"));


        List<DVDInfo> result = kioskServiceUS.searchByTitle(searchTitle);

        assertThat(result.size(), is(1));

        DVDInfo dvdInfo = result.get(0);

        int loandID = kioskServiceUS.loanDVD(dvdInfo.getId(), username, password, new Date(), locationId);
        logger.info("Loan added underNumber: {}", loandID);
        assertThat(loandID, is(-1));
    }

    @Test
    public void test__search_by_title_NoResults() {

        String searchTitle = "Blazing Saddles";

//        kioskServiceUS = applicationContext.getBean("kioskServiceUS", KioskService.class);

//        assertThat(kioskServiceUS, is(notNullValue()));

        List<DVDInfo> result = kioskServiceUS.searchByTitle(searchTitle);

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(0));
    }


    @Test
    public void test__search_by_title_2() {
        String searchTitle = "Phone Booth";
        String username = "j.wirth";
        String password = "whoknows";
        String locationId = locationUS;

        List<DVDInfo> result = kioskServiceNL.searchByTitle(searchTitle);

        // TODO: set to > 0
        assertThat(result.size(), is(1));

        DVDInfo dvdInfo = result.get(0);

        int loanID = kioskServiceNL.loanDVD(dvdInfo.getId(), username, password, new Date(), locationId);
        logger.info("Loan added underNumber: " + loanID);
        assertThat(loanID, is(9494));
    }


    @Test
    public void test__Get_DVDs_By_LocationID() {
        List<DVDInfo> result = kioskServiceNL.getDVDsByLocationID(locationUS);
        assertThat(result.size(), is(5));
    }


    @Test
    public void testSetterAndGetter() {

        KioskServiceImpl impl = (KioskServiceImpl) kioskServiceUS;

        impl.setAirportLocation(airportLocationDAO.getLocationByID(locationFR));
        assertThat(impl.getAirportLocation(), is(airportLocationDAO.getLocationByID(locationFR)));

        impl.setDvdInfoDAO(dvdInfoDAO);
        assertThat(impl.getDvdInfoDAO(), is(dvdInfoDAO));

        impl.setDvdLocationDAO(dvdLocationDAO);
        assertThat(impl.getDvdLocationDAO(), is(dvdLocationDAO));

        impl.setLoanDAO(loanDAO);
        assertThat(impl.getLoanDAO(), is(loanDAO));

        impl.setMemberDAO(memberDAO);
        assertThat(impl.getMemberDAO(), is(memberDAO));
    }


} // The End...
