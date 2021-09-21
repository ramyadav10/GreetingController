package com.bridgelabz.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.app.model.User;

@RestController
public class GreetingController {
	
	private List<User> list=new ArrayList<User>();
	
	private AtomicLong atomicLong=new AtomicLong();
	
//	Get all user
	@GetMapping
	public List<User> getAll() {
		return list;
	}
	
//	Get user by id
	@GetMapping("/{id}")
	public String getById(@PathVariable int id) {
		User user=list.stream().filter(e->e.getId()==id).findFirst().get();
		return "hello "+user.getFirstName();
	}
	
//	Create user
	@PostMapping
	public String create(@RequestBody User user) {
		user.setId(atomicLong.incrementAndGet());
		list.add(user);
		return "hello "+user.getFirstName()+" added";
	}
	
//	Update user by id
	@PutMapping("/{id}")
	public String putById(@RequestBody User user,@PathVariable int id) {
		User show=list.get(id-1);
		show.setFirstName(user.getFirstName());
		return "hello "+show.getFirstName();
	}
	
//	Delete user by id
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable int id) {
		list.remove(id-1);
		return "delted Id ->"+id;
	}
	
	
	
}
