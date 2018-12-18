package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

	@Autowired
	UserService userService ;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	RedisTemplate<Object,User> userRedisTemplate;
	
	
	@Test
	public void test01(){
		User user =userService.getUser(3l);
		userRedisTemplate.opsForValue().set("o4", user);
		
		 
	}
	
	@Test
	public void test02(){
		stringRedisTemplate.opsForValue().append("a2", "设施");
	}
	
	@Test
	public void contextLoads() {
	}

}
