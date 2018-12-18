package com.example.demo.mq.hello;

import java.util.ArrayList;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.User;



@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {
	 @RabbitHandler
	 public void process(User hello1) {
//		 List list = new ArrayList();
		 System.out.println("Receiver  : " + hello1);
	 }
	 
}
