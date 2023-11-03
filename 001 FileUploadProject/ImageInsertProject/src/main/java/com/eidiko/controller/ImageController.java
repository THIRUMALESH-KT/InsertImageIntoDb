package com.eidiko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.entity.ImageE;
import com.eidiko.service.ImageService;

@RestController
public class ImageController {

	@Autowired
	private ImageService service;
	@GetMapping("/get")
	public ResponseEntity<ImageE> get(){
		return new ResponseEntity<ImageE>(service.get(),HttpStatus.OK);
	}
	@PostMapping("/save")
	public ResponseEntity<ImageE> save(){
		return new ResponseEntity<ImageE>(service.save(),HttpStatus.OK);
		
	}
}
