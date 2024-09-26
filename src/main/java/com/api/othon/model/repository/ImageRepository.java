package com.api.othon.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.othon.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}