package com.springclass.jdbc.dao;


import com.springclass.dao.MemberDAO;
import com.springclass.domain.InvalidMemberPasswordException;
import com.springclass.domain.Member;
import com.springclass.domain.MemberException;
import com.springclass.domain.UnknownMemberException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 * CREATE TABLE MEMBER(
 * MEMBER_ID VARCHAR(10) NOT NULL PRIMARY KEY,
 * USERNAME VARCHAR(20) NOT NULL,
 * PASSWORD VARCHAR(10),
 * STREET VARCHAR(30),
 * CITY VARCHAR(30),
 * ZIPCODE VARCHAR(20),
 * COUNTRY VARCHAR(30),
 * FFNUMBER VARCHAR(30),
 * AIRLINECODE VARCHAR(3),
 * EMAIL VARCHAR(30),
 * STATUS CHAR(1),
 * FULLNAME VARCHAR(40)
 * )
 * </p>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2016 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
@Repository
public class MemberDAOImpl implements MemberDAO {

//    @Autowired
//    private MemberFixture memberFixture;

//    @Autowired
//    private DataSource dataSource;
//
//    @PostConstruct
//    private void initialize() {
//        setDataSource(dataSource);
//    }

    private static final List<Member> members;

    static {
        members = new ArrayList<>();
        members.add(new Member("93941", "j.smith", "hiking", "w 49th St.", "New York", "10019", "U.S.A.", "1010-695-3405", "AAE", "jsmith@xs4all.com", 'A', null));
        members.add(new Member("93947", "j.wirth", "whoknows", "121 Grandview Rd", "Braintree", "2184", "U.S.A.", "1003-345-8754", "ATN", "jwirth@tiscali.com", 'A', null));
    }

    @Override
    public String getMemberID(String userName, String password) throws MemberException {
        for (Member member : members) {
            if (userName.equals(member.getUserName())) {
                if (password.equals(member.getPassword())) {
                    return member.getId();
                } else {
                    throw new InvalidMemberPasswordException("Invalid password provided");
                }
            }
        }
        throw new UnknownMemberException(userName + " is not registered in system");
    } // end getMemberID

} // The end...
