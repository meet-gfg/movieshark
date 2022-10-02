package com.gfg.movieshark.controller;

import com.gfg.movieshark.resource.TheaterResource;
import com.gfg.movieshark.service.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	@PostMapping("/add")
	public ResponseEntity<TheaterResource> addUser(@RequestBody TheaterResource theaterResource) {

		return ResponseEntity.ok(theaterService.addTheater(theaterResource));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TheaterResource> getUser(@PathVariable(name = "id") @Min(value = 1, message = "Theater Id Cannot be -ve") long id) {

		return ResponseEntity.ok(theaterService.getTheater(id));
	}
}