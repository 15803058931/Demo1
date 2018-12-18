package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

//@RestController 只能返回内容，不能返回到页面
@Controller
public class HelloController {

	@Resource
	UserService userService;
	//用model记录值，返回到页面
	//{type} ： 定义URL变量    例如： /hello/{type}  对应 http://xxx//hello/save 则 type是 save
	//@PathVariable 取得URl变量的具体值
	//demo访问地址为 http://127.0.0.1:8080/hello/save?size=22
	@RequestMapping("/hello/{type}")
	public String hello(Model model,@RequestParam(value = "size", defaultValue = "15") int size
			,@PathVariable(value="type") String type) {
		
		try {
			System.out.println("com.mysql.cj.jdbc.Driver"+Class.forName("com.mysql.cj.jdbc.Driver"));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		  User user=new User();
	        user.setName("小明1");
	        String str="<div style=\"border: 5px solid green\">发发发</div>";
	        
	        List<String> list = new ArrayList<String>();
	        
//	     	  自定义sql查询
	       int c = userService.countByAge(16);
	       
//	       JPA自带的简单查询
	       User user1= userService.getUserByAddress("三生三世").get(0);
//	       JPA自带的保存
	       User user2 = new User();
	       user2.setAddress("阿斯顿");
	       user2.setName("王文英");
	       user2.setAge(20);
	       user2 =userService.saveUser(user2);//JPA的save会自动传回数据库的id
//	       JPA自带的修改	,如果ID不存在则为新增       
	       User user3 = new User();
	       user3.setAddress("阿斯顿2");
	       user3.setName("王文英2");
	       user3.setAge(202);
	       user3.setId(666l);
	       user3 = userService.updateUser(user3);
	       
	      User user0 = userService.getUser(1L);
	       
//	       带事务的业务层方法，正常情况下应执行回滚
	       User user4 = new User();
	       user4.setAddress("对对对");
	       user4.setName("哥哥哥2");
	       user4.setAge(100);
	       user4.setId(5l);
	       try {
			userService.transactionDemo(user4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	       //JPA自带的删除	,如果ID不存在则抛出异常
//	       try {
//			userService.deleteById(10l);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	       //分页查询
	       int page=1;
	       int pageSize=3;
	       Sort sort = new Sort(Direction.DESC, "id");
	       Pageable pageable = new PageRequest(page, pageSize, sort);
	       Page<User> users = userService.pageByNameDemo(null, pageable);
	       
	       Page<User> users1= userService.getAll(pageable);
	       System.out.println(users);
	       System.out.println(users1);
	        String s1="s1";
	        String s2="s2";
	        list.add(s1);
	        list.add(s2);	        
	        model.addAttribute("user",user);
	        model.addAttribute("size",size);
	        model.addAttribute("type",type);
	        model.addAttribute("str",str);
	        model.addAttribute("list",list);
	        model.addAttribute("c",c);
	        model.addAttribute("user1",user1);
	        model.addAttribute("user2",user2);
	        model.addAttribute("user3",user3);
	        return "/home";
	}
	
	
    //Save the uploaded file to this folder
	//RedirectAttributes是SpringMVC3.1版本之后出来的一个功能，专门用于重定向之后还能带参数跳转的
    private static String UPLOADED_FOLDER = "d://temp//";
	@PostMapping("/upload")
	public String uploadHandle(@RequestParam("file") MultipartFile[] files,RedirectAttributes redirectAttributes) {
		   if (files.length==0) {
		        redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
		        return "redirect:uploadStatus";
		    }
		
		   for(int i=0;i<files.length;i++) {
			   MultipartFile file =files[i];
			   try {
				   
					byte[] bytes = file.getBytes();
					Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
					Files.write(path, bytes);
					redirectAttributes.addFlashAttribute("message",
			                "You successfully uploaded '" + file.getOriginalFilename() + "'");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		   }
		   

		   
		return "redirect:/uploadStatus";
	}
	
	@RequestMapping("/uploadStatus")
    public String uploadStatus() {
//    	String s1 = (String) redirectAttributes.getFlashAttributes().get("message");
    	  
        return "uploadStatus";
    }
	
	@GetMapping("/")
	public String index() {
	    return "upload";
	}
}
