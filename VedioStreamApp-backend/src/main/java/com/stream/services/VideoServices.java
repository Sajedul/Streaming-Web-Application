package com.stream.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stream.entities.Video;

public interface VideoServices {
	Video save (Video video,MultipartFile file);
	Video get(String videoId);
	Video getByTitle(String title);
	List<Video>getAll();
	
}
