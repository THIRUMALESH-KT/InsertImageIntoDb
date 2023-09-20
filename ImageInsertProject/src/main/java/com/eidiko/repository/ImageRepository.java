package com.eidiko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eidiko.entity.ImageE;

public interface ImageRepository extends JpaRepository<ImageE, Long> {

	List<ImageE> findAllByName(String name);

}
