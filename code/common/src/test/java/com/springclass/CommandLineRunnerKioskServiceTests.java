package com.springclass;

import com.springclass.bo.KioskService;
import com.springclass.dao.DVDInfoDAO;
import com.springclass.domain.DVDInfo;
import org.junit.Before;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class CommandLineRunnerKioskServiceTests {

    private final Logger logger = LoggerFactory
            .getLogger(CommandLineRunnerKioskServiceTests.class);

    //@Autowired
    ApplicationContext applicationContext;

    //@Autowired
    KioskService kioskServiceUS;

    //@Autowired
    KioskService kioskServiceNL;


    //@Value("${location.id.us}")
    private String locationUS;
    //@Value("${location.id.nl}")
    private String locationNL;
    //@Value("${location.id.fr}")
    private String locationFR;


    public void main(String[] args) {


        String searchTitle = "2 Fast 2 Furious (Widescreen Edition)";
        String username = "j.wirth";
        String password = "whoknows";
        String locationId = locationNL;


        List<DVDInfo> result = kioskServiceUS.searchByTitle(searchTitle);

        // TODO: set to > 0
        assert (result.size() == 1);

        DVDInfo dvdInfo = result.get(0);

        int loandID = kioskServiceUS.loanDVD(dvdInfo.getId(), username, password, new Date(), locationId);
        logger.info("Loan added underNumber: " + loandID);
    }

    //@Test
    public void testUSA_MemberException() {
        logger.info("testUSA_MemberException");

        String searchTitle = "2 Fast 2 Furious (Widescreen Edition)";
        String username = "foo";
        String password = "bar";
        String locationId = locationNL;


        //Create Mock for dvdInfoDAO
//        dvdInfoDAO = new DVDInfoDAOImpl();
//        ((KioskServiceImpl) kioskServiceUS).setDvdInfoDAO(dvdInfoDAO);

        List<DVDInfo> result = kioskServiceUS.searchByTitle(searchTitle);

        // TODO: set to > 0
        assertThat(result.size(), is(1));

        DVDInfo dvdInfo = result.get(0);

        int loandID = kioskServiceUS.loanDVD(dvdInfo.getId(), username, password, new Date(), locationId);
        logger.info("Loan added underNumber: " + loandID);
        assertThat(loandID, is(-1));
    }

    //@Test
    public void testUSA_Search_NoResults() {
        logger.info("testUSA_Search_NoResults");

        String searchTitle = "Blazing Saddles";
        String username = "j.wirth";
        String password = "whoknows";
        String locationId = locationUS;


        //Create Mock for dvdInfoDAO
//        dvdInfoDAO = new DVDInfoDAOImpl();
//        ((KioskServiceImpl) kioskServiceUS).setDvdInfoDAO(dvdInfoDAO);

        List<DVDInfo> result = kioskServiceUS.searchByTitle(searchTitle);

        assertThat(result.size(), is(0));
    }


    //@Test
    public void testNL() {
        logger.info("testNL");

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


} // The End...
