package com.eidiko.service;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.FileUpload;
import com.eidiko.entity.ImageE;
import com.eidiko.repository.FileRepository;

@Service
public class FileImpl implements FileService {

	@Autowired 
   private	FileRepository repo;
	


	@Override
	public String save(String fileName, MultipartFile file) throws IOException, SQLException {
		// TODO Auto-generated method stub
       // String fileName = file.getOriginalFilename();

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFile(new javax.sql.rowset.serial.SerialBlob(file.getBytes()) );
        fileUpload.setFileName(fileName);
        repo.save(fileUpload);

        return "File uploaded successfully: " + fileName;
   }
	
	@Override
	public FileUpload get(String name) {
		// TODO Auto-generated method stub
		return repo.findByFileName(name);
	}
//fileName
}
