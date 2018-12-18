package com.example.demo.mq.hello;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.User;

@Component
public class HelloSender {
	@Resource
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hello " + new Date();
		
		User user = new User();
		user.setName("张三");
		user.setAge(25);
		user.setAddress("合肥公司");
		System.out.println("Sender : " + user);
		this.rabbitTemplate.convertAndSend("hello", user);
	}
}
