package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

}
