package com.example.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.demo.pojo.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@Service("userService")
@Scope("prototype")
public class UserServiceJpaImpl implements UserService{
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceJpaImpl.class);


	@Resource
	UserRepository userRepository;	
	
	@Override
	public List<User> getUserByAddress(String address) {
		log.info("getUserByAddress 进入jpa");
		return userRepository.findByAddress(address);
	}
	
	@Override
	public int countByAge(int age) {
		return userRepository.countUserByAge(age);
	}
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public void transactionDemo(User user) {
		userRepository.save(user);
		
		if(user.getId().longValue() == 5) {
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		}else {
			userRepository.deleteById(user.getId());
		}
		user.setId(99l);
		user.setName("不应该有的");
		userRepository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override

	public Page<User> pageByNameDemo(String name,Pageable pageable) {
		
		Page<User>  users= userRepository.findUserByName(pageable, name);
		return users;
	}

	@Override
	public Page<User> getAll(Pageable page) {
		return userRepository.findAll(page);
	}
	
	public int test(int i) {
		return ++i;
	}

	@Override
//	@Cacheable(cacheNames="user",key="#id")
	public User getUser(Long id) {
		log.info("mysql.getUser");
		return userRepository.findUserById(id);
	}
	
 

}
