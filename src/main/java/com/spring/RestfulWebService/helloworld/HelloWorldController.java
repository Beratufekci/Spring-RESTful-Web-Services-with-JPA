package com.spring.RestfulWebService.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


//Controller(1)
@RestController
public class HelloWorldController{
	
	@Autowired
	private MessageSource messageSource;
	
	//GET
	//URI - /hello-world(2)
	@GetMapping(path="/hello-world" )
	public String helloWorld() {
		
		return "Hello!! It's Berat";
		
	}
	
	@GetMapping(path="hello-world/firstTry/{name}" )
	public HelloWorldBean firstTry(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Welcome "+ name));
	}
	
	//URI - /hello-world-bean
	@GetMapping(path="/hello-world-bean" )
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello!! It's Berat");
		
	}

	//URI - /hello-world/Berat
	@GetMapping(path="/hello-world/{name}" )
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello!! It's " + name));
		
	}

	
	@GetMapping(path="/hello-world-internationalized" )
	public String helloWorldInternationalized() {
		
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
		
	}

}


