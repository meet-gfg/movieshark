package com.gfg.movieshark.controller;

import com.gfg.movieshark.resource.UserResource;
import com.gfg.movieshark.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<UserResource> addUser(@RequestBody UserResource userResource) {
		return ResponseEntity.ok(userService.addUser(userResource));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResource> getUser(@PathVariable(name = "id") @Min(value = 1, message = "User Id Cannot be -ve") long id) {
		return ResponseEntity.ok(userService.getUser(id));
	}

}