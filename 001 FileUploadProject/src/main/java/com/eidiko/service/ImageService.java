package com.eidiko.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.ImageE;

public interface ImageService {

	public ImageE save(String name, MultipartFile file) throws IOException, SerialException, SQLException;
	ImageE get(String name);
	List<byte[]> findAllImagesByName(String name);
}
