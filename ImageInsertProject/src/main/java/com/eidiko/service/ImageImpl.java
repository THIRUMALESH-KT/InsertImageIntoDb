package com.eidiko.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.ImageE;
import com.eidiko.repository.ImageRepository;

@Service
public class ImageImpl implements ImageService {

	@Autowired
	private ImageRepository repo;
	


	@Override
	public List<ImageE> get(String name) {
		// TODO Auto-generated method stub
		return repo.findAllByName(name);
	}
	   @Override
	    public ImageE save(String name, MultipartFile file) throws SerialException, SQLException, IOException {
	        ImageE image = new ImageE();
	        image.setName(name);
	        image.setImage(new javax.sql.rowset.serial.SerialBlob(file.getBytes()));
	        return repo.save(image);
	    }

}
