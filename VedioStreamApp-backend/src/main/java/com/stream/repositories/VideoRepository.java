package com.stream.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stream.entities.Video;

public interface VideoRepository extends JpaRepository<Video, String> {
	
	Optional<Video>findByTitle(String title);
}
