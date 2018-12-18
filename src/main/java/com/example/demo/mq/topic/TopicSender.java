package com.example.demo.mq.topic;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send() {
		String str="hi, i am message all";
		System.out.println("Sender : " + str);
		rabbitTemplate.convertAndSend("topicExchange","topic.1",str);
	}
	
	public void send1() {
		String context = "hi, i am message 1";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context);
	}

	public void send2() {
		String context = "hi, i am messages 2";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", context);
	}
}
