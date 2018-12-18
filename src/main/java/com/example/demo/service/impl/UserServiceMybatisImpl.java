package com.example.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

//@Service("userService")
public class UserServiceMybatisImpl implements UserService{
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceMybatisImpl.class);


	@Autowired
	UserMapper userMapper;
	@Override	
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		log.info("Mybatis.getUser");
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getUserByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByAge(int age) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transactionDemo(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<User> pageByNameDemo(String name, Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int test(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
