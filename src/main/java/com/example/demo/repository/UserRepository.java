package com.example.demo.repository;

import java.util.List;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.pojo.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public List<User> findByAddress(String address);
	
//	public Optional<User>  findByAddress(Long id);
	
	public User findUserById(Long id);
	
	public List<User> findUserByName(String name);
	
	public List<User> findUserByAge(int age);
	
	@Query(" select count(1) as num from User where age=?1 ")
	public int countUserByAge(int age);
	
	public void deleteById(Long id);
	
	Page<User> findUserByName(Pageable pageable,String name);
	
	Page<User> findAll(Pageable pageable);
}
