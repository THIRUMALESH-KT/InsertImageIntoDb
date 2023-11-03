package com.eidiko.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eidiko.entity.FileUpload;
public interface FileRepository extends JpaRepository<FileUpload,Long> {

public FileUpload save(FileUpload file);

public FileUpload findByFileName(String name);

}
