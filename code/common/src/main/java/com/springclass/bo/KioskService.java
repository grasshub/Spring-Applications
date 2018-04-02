package com.springclass.bo;

import com.springclass.domain.DVDInfo;

import java.util.Date;
import java.util.List;

public interface KioskService {

    List<DVDInfo> getDVDsByLocationID(String locationID);

    List<DVDInfo> searchByTitle(String title);

    /**
     * FIXME: Need to add 'throws MemberException' to this signature.
     * FIXME: the issue is there are many changes in the code to do this.
     * @param dvdTitleID
     * @param userName
     * @param password
     * @param returnDate
     * @param returnLocationID
     * @return
     */
    Integer loanDVD(String dvdTitleID,
                String userName,
                String password,
                Date returnDate,
                String returnLocationID);

} // The End...
