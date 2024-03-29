package com.spring.RestfulWebService.user;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.spring.RestfulWebService.post.Post;
import com.spring.RestfulWebService.post.PostRepository;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	//GET /jpa/users
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	//Get /jpa/users/{id}
	@GetMapping("/jpa/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			
			throw new UserNotFoundException("id-" + id);
			
		}
		
		return user;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
	  userRepository.deleteById(id);
	  
	}
	

	@PostMapping("/jpa/post-user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
		User savedUser = userRepository.save(user);
		
		//CREATED
		// /jpa/users/{id} - savedUser.getId() 
		URI location = ServletUriComponentsBuilder
		    .fromCurrentRequest()
		    .path("/{id}")
		    .buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
	//GET /jpa/users
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveUserAllPost(@PathVariable int id){
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			
			throw new UserNotFoundException("id-" + id);
			
		}
		
		return userOptional.get().getPosts(); 
		
		
	}
	


	@PostMapping("/jpa/post-user/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable  int id,@RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			
			throw new UserNotFoundException("id-" + id);
			
		}
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		
		
		//CREATED
		// /jpa/users/{id} - savedUser.getId() 
		URI location = ServletUriComponentsBuilder
		    .fromCurrentRequest()
		    .path("/{id}")
		    .buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
	@DeleteMapping("/jpa/posts/{id}")
	public void deletePost(@PathVariable int id) {
		
		postRepository.deleteById(id);
	}
	

	
}