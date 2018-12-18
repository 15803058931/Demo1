package com.example.demo.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.pojo.User;


public interface UserService {

	@Cacheable(cacheNames="user",key="#id",cacheManager="userCacheManager" )
	public User getUser(Long id);
	
	@Cacheable(cacheNames="user",key="#address" )
	public List<User> getUserByAddress(String address);
	public int countByAge(int age);
	public User saveUser(User user);
	public User updateUser(User user);
	public void transactionDemo(User user);
	public void deleteById(Long id);
	public Page<User> pageByNameDemo(String name,Pageable page);	
	
	public Page<User> getAll(Pageable page);
	
	public int test(int i);
}
