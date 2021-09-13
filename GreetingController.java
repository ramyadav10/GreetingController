package com.bridgelabz.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.app.model.Greeting;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@RestController
@RequestMapping("/greetinggo")
public class GreetingController {
//	creating atomicLong
	private final static AtomicLong atomicLong=new AtomicLong();
	public List<Greeting> list=new ArrayList<Greeting>();
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
		return new Greeting(atomicLong.incrementAndGet(),"hello "+name);
	}

	@PostMapping()
	public void letsGreetByName(@RequestBody Greeting greeting) {
		greeting.setCounter(atomicLong.incrementAndGet());
		list.add(greeting);
		 System.out.println("user id ->"+greeting.getCounter()+"user name ->"+greeting.getNameString());
	}
	
	@GetMapping
	public ResponseEntity<List<Greeting>> getDetails() {
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Greeting> getDetailsById(@PathVariable long id){
		Optional<Greeting> obj= list.stream().filter(e ->e.getCounter()==id).findFirst();
		if(obj.isPresent()) {
			return new ResponseEntity<>(obj.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Greeting> updateDetails(@RequestBody Greeting greeting,@PathVariable long id) {
		Greeting optional=list.stream().filter(e -> e.getCounter()==id).findFirst().get();
		optional.setNameString(greeting.getNameString());
		return new ResponseEntity<>(optional,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		Greeting greeting=list.stream().filter(e ->e.getCounter()==id).findFirst().get();
		list.remove(greeting);
	}
	
	
}
