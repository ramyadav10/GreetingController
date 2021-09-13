package com.bridgelabz.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.app.model.Greeting;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

@RestController
public class GreetingController {

	private final static AtomicLong atomicLong=new AtomicLong();
	public List<Greeting> list=new ArrayList<Greeting>();
	

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
		return new Greeting(atomicLong.incrementAndGet(),"hello "+name);
	}
	
	
	
}
