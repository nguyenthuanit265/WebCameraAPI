package com.myclass.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.myclass.dto.PhotoDto;
import com.myclass.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	@Query("SELECT new com.myclass.dto.PhotoDto(u.namePhoto, u.url) FROM Photo u WHERE u.userId = :id")
	public ArrayList<PhotoDto> getFile(@RequestParam("id") int id);

}
