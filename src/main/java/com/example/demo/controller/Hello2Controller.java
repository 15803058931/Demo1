package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

/**
 * 测试spring缓存
 * @author Administrator
 *
 */
@RestController
public class Hello2Controller {
	@Autowired
	UserService userService;

	@RequestMapping("/hello2/{id}")
	public User getUser(@PathVariable Long id) {
		
		return userService.getUser(id);
	}
	
	@RequestMapping("/hello3/{add}")
	public List<User> getUserListByAddr(@PathVariable String add ){
		 List<User>  list =userService.getUserByAddress(add);
		return list;
	}
}
