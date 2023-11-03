package com.eidiko.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.FileUpload;
@Service
public interface FileService {

	public FileUpload get(String name);
	//public  List<FileUpload> findAll();
	public String save(String fileName, MultipartFile file) throws IOException, SQLException;
}
