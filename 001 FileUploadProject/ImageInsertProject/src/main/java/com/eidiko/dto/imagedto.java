package com.eidiko.dto;

import java.sql.Blob;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class imagedto {

	@NotNull
	private String name;
   @Lob
   @jakarta.validation.constraints.NotNull
   @Pattern(regexp = "image/png")   
	private Blob image;
public imagedto() {
	super();
	// TODO Auto-generated constructor stub
}
public imagedto(@NotNull String name, @NotNull @Pattern(regexp = "image/png") Blob image) {
	super();
	this.name = name;
	this.image = image;
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
