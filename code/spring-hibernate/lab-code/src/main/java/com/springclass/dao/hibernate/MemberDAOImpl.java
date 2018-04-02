package com.springclass.dao.hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springclass.dao.MemberDAO;
import com.springclass.model.Member;

/**
 * <p/>
 * This component and its source code representation are copyright protected and
 * proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 *
 * Copyright (c) 2015 The Trivera Group, LLC. http://www.triveratech.com
 * http://www.triveragroup.com
 * </p>
 * 
 * @author The Trivera Group Tech Team.
 */
@Repository
@Transactional(readOnly = false)
public class MemberDAOImpl implements MemberDAO {
	
	/*
	 * Autowire the hibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(member);
	}

	@Override
	public void deleteMember(Member member) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(member);
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		return (List<Member>) hibernateTemplate.find("from Member");
	}

}
