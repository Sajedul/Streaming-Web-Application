package com.stream.services.impl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stream.entities.Video;
import com.stream.repositories.VideoRepository;
import com.stream.services.VideoServices;

import jakarta.annotation.PostConstruct;

@Service
public class VideoServiceImpl implements VideoServices {
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Value("${files.video}")
	String DIR;
	
	@PostConstruct
	public void init() {
		File file = new File(DIR);
		if(!file.exists()) {
			file.mkdir();
			System.out.println("Folder Created");
		}else {
			System.out.println("Folder Already created");
		}
	}

	@Override
	public Video save(Video video, MultipartFile file) {
		
		try {
			//get original file name
			
			String originalFilename = file.getOriginalFilename();
			String contentType = file.getContentType();
			InputStream inputStream = file.getInputStream();
			
			//create file path
			String cleanFileName = StringUtils.cleanPath(originalFilename);
			
			//create folder path
			String cleanPath = StringUtils.cleanPath(DIR);
			
			//folder path with file name
			Path path = Paths.get(cleanPath,cleanFileName);
			
			System.out.println(contentType);
			
			System.out.println(path);
			
			//copy file to the folder
			
			Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);
			
			//video meta data
			video.setContentType(contentType);
			video.setFilePath(path.toString());
			
			//meta data save
			return videoRepository.save(video);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public Video get(String videoId) {
		Video video = videoRepository.findById(videoId).orElseThrow(()->new RuntimeException("video not found"));
		return video;
	}

	@Override
	public Video getByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> getAll() {
		return videoRepository.findAll();
	}

}
