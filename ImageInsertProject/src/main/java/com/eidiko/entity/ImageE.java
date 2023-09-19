package com.eidiko.entity;

import java.sql.Blob;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class ImageE {
	@jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String name;
   
	private Blob image;
public ImageE() {
	super();
	// TODO Auto-generated constructor stub
}
public ImageE( String name, Blob image) {
	super();
	this.name = name;
	this.image = image;
}
public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Blob getImage() {
	return image;
}
public void setImage(Blob image) {
	this.image = image;
}
   
   
   
}
