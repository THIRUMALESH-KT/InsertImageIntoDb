package com.eidiko.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.ImageE;
import com.eidiko.service.ImageService;

@RestController
public class ImageController {

	@Autowired
	private ImageService service;
	@GetMapping("/get")
	public ResponseEntity<byte[]> get(@RequestParam("name") String name) throws SQLException{
		   List<ImageE> images = service.get(name);
		   System.out.println("Number of images retrieved: " + images.size());

		   for (ImageE image : images) {
			   System.out.println("Image name: " + image.getName());
		   }
		    if (!images.isEmpty()) {
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        List<byte[]> list=new ArrayList<>();

		        try {
		            for (ImageE image : images) {
		                byte[] imageData = image.getImage().getBytes(1, (int) image.getImage().length());
		                baos.write(imageData);
		                list.add(imageData);
		            }
		            byte[] allImageData = baos.toByteArray();
		            return ResponseEntity.ok() .contentType(MediaType.IMAGE_JPEG).body(allImageData);
		        } catch (IOException e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		        }
		    } else {
		        return ResponseEntity.notFound().build();
		    }
	}
	@PostMapping("/save")
	public ResponseEntity<ImageE> save(@RequestParam("name") String name,
		    @RequestParam("image") MultipartFile image) throws IOException, SerialException, SQLException{
		return new ResponseEntity<ImageE>(service.save(name,image),HttpStatus.OK);
		
	}
}
