package com.springclass.dao;

import com.springclass.domain.Member;
import com.springclass.domain.MemberException;

import java.util.List;

/**
 * <p/>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 *
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 * @author The Trivera Group Tech Team.
 */
public interface MemberDAO {

    String getMemberID(String userName, String password) throws MemberException;

//    void addMember(Member member);

//    List<Member> getAllMembers();

}
