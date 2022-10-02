package com.gfg.movieshark.controller;

import com.gfg.movieshark.resource.ShowResource;
import com.gfg.movieshark.service.ShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private ShowService showService;

	@GetMapping("/search")
	public ResponseEntity<List<ShowResource>> search(
			@RequestParam(name = "movieName", required = false) String movieName) {
		return ResponseEntity.ok(showService.searchShows(movieName));
	}

	@PostMapping("/add")
	public ResponseEntity<ShowResource> addShow(@RequestBody ShowResource showResource) {
		showService.addShow(showResource);
		return ResponseEntity.ok(showResource);
	}

}