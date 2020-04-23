package com.goaudits.business.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.goaudits.business.entity.User;
import com.goaudits.business.mapper.SetupMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	SetupMapper userMapper;

	@Override
//	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 User user = userMapper.getUserDetails(username);

		return UserPrinciple.build(user);
	}
}