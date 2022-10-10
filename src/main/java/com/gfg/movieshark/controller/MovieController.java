package com.gfg.movieshark.controller;

import com.gfg.movieshark.resource.MovieResource;
import com.gfg.movieshark.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("/add")
	public ResponseEntity<MovieResource> addMovie(@RequestBody MovieResource movieRequest) {

		//return ResponseEntity.ok(movieService.addMovie(movieRequest));
		return new ResponseEntity<>(movieService.addMovie(movieRequest), HttpStatus.CREATED); // 201 CREATED;
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieResource> getMovieById(@PathVariable(name = "id") long id) {

		return ResponseEntity.ok(movieService.getMovie(id));
	}

	@GetMapping("/title")
	public ResponseEntity<MovieResource> getMovieByTitle(@RequestParam String title) {

		return ResponseEntity.ok(movieService.getMovie(title));
	}
}