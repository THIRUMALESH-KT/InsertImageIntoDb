package com.eidiko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eidiko.entity.ImageE;

public interface ImageRepository extends JpaRepository<ImageE, Long> {

	ImageE findByName(String name);
	@Query("SELECT i.image FROM ImageE i WHERE i.name = :name")
    List<byte[]> findImageByName(String name);

}
