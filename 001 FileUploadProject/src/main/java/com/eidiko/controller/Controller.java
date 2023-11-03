package com.eidiko.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.FileUpload;
import com.eidiko.entity.ImageE;
import com.eidiko.service.FileService;
import com.eidiko.service.ImageService;

@RestController
public class Controller {

	@Autowired
	private ImageService service;
	@Autowired
	private FileService fileService;
	@GetMapping("/get")
	public ResponseEntity<byte[]> get(@RequestParam String name) throws SQLException{
		 ImageE image = service.get(name);
		// System.out.println(image.getImage());
	//service.findAllImagesByName(name);
		    if (image!=null) {
		    	System.out.println("found");
		                byte[] imageData = image.getImage().getBytes(1, (int) image.getImage().length());		            
		            return ResponseEntity.ok() .contentType(org.springframework.http.MediaType.IMAGE_PNG).body(imageData);
		    } else {
		    	System.out.println("not found");
		        return ResponseEntity.notFound().build();
		    }
	}
//	@GetMapping("/getAllImages")
//	public ResponseEntity<byte[]> getAll(@RequestParam("name") String name) throws IOException {
//	    List<byte[]> images = service.findAllImagesByName(name);
//
//	    if (images.isEmpty()) {
//	        return ResponseEntity.notFound().build();
//	    }
//
//	    byte[] combinedImageBytes = ImageUtils.combineImages(images);
//
//	    if (combinedImageBytes == null) {
//	        return ResponseEntity.notFound().build();
//	    }
//
//	    return ResponseEntity.ok()
//	            .contentType(MediaType.IMAGE_PNG)
//	            .body(combinedImageBytes);
//	   }
	@PostMapping("/saveImage")
	public ResponseEntity<?> save( @RequestParam("image") MultipartFile image) throws IOException, SerialException, SQLException{
		String originalfilename=image.getOriginalFilename();
		System.out.println(originalfilename.replace(".png", ""));
		if(originalfilename!=null && originalfilename.endsWith(".png")) {
		String	filename2=originalfilename.replace(".png", " ");
		System.out.println(filename2);
			service.save(filename2,image);
			return new ResponseEntity<String>(originalfilename+ " image is saved",HttpStatus.OK);

		}
		
		else return new  ResponseEntity<String>("only png files allowed",HttpStatus.OK);
		
	}
	  @PostMapping("/uploadFile")
	    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, SerialException, SQLException {
		  String originalFilename=file.getOriginalFilename();
		  System.out.println(originalFilename);
	        if (originalFilename!=null &&originalFilename.endsWith(".txt")) {
	      String fileName=originalFilename.replace(".txt", "");
	        fileService.save(fileName, file);
	            return ResponseEntity.ok("File uploaded successfully: " + fileName);
	        } else {
	            return ResponseEntity.badRequest().body("File upload failed: .txt extention files only allowed");
	        }
	    }
	  @GetMapping("/sendImageToLocalStorate")
	  public ResponseEntity<String> sendToLocalStorage(@RequestParam String name){
		    String storagePath = "C:\\Users\\Sreenivas Bandaru\\Downloads\\DBImageFiles";
		    ImageE image = service.get(name);

		    if (image != null) {
		        try {
		            byte[] imageData = image.getImage().getBytes(1, (int) image.getImage().length());

		            String filePath = storagePath + "\\" + name; 
		            try (FileOutputStream fos = new FileOutputStream(filePath)) {
		                fos.write(imageData);
		            } catch (IOException e) {
		                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving the image to local storage");
		            }
		            return ResponseEntity.ok("File downloaded to local storage: " + filePath);
		        } catch (SQLException e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving the image from the database");
		        }
		    } else {
		    	System.out.println("iamge is null");
		        return ResponseEntity.notFound().build();
		    }
	  }
	  @GetMapping("/sendFileToLocalStorate")
	  public ResponseEntity<String> sendFileToLocalStorage(@RequestParam String name){
		    String storagePath = "C:\\Users\\Sreenivas Bandaru\\Downloads\\DBTextFiles";
		    FileUpload image = (FileUpload) fileService.get(name);
		    if (image != null) {
		        try {
		            byte[] imageData = image.getFile().getBytes(1, (int) image.getFile().length());

		            String filePath = storagePath + "\\" + name; 
		            try (FileOutputStream fos = new FileOutputStream(filePath)) {
		                fos.write(imageData);
		            } catch (IOException e) {
		                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving the image to local storage");
		            }
		            return ResponseEntity.ok("File downloaded to local storage: " + filePath);
		        } catch (SQLException e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving the image from the database");
		        }
		    } else {
		    	System.out.println("File is null");
		        return ResponseEntity.notFound().build();
		    }
	  }
	  

		@GetMapping("/getFiles")
		public ResponseEntity<byte[]> getFiles(@RequestParam("name") String name) throws SQLException{
			   FileUpload file = fileService.get(name);
			
			    if (file!=null) {
			           byte[] FileData = file.getFile().getBytes(1, (int) file.getFile().length());		            
			            return ResponseEntity.ok() .contentType(org.springframework.http.MediaType.TEXT_EVENT_STREAM).body(FileData);
			    }else {
			        return ResponseEntity.notFound().build();
			    }
		}
}
