package com.example.demo.controller;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aspect.MyPoint;
import com.example.demo.service.UserService;

@RestController
public class AopTestController {
	@Resource
	UserService userService ;
	
	@MyPoint
	@RequestMapping("/first")
	public Object first(Model model,@RequestParam(value = "size", defaultValue = "15") int size
			) {
		
		return "first controller"+userService.test(22);
	}

	@RequestMapping("/doError")
	public Object error()   {
		Integer o= 1/0;
		return o;
	}
}
