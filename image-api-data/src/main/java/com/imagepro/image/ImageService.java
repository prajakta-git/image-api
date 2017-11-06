package com.imagepro.image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	
	public List<Image> getAllImages(){
		List images = new ArrayList<>();
		imageRepository.findAll().forEach(images::add);
		return images;
	}

	public Image getImage(Integer id){
		return imageRepository.findOne(id);
	}

	public Integer addImage(Image image) {
		Integer id = (int)(Math.random()*10);
		image.setimageId(id);
		imageRepository.save(image);
		return id;
	}

	public void updateImage(Image image) {
		imageRepository.save(image);
		
	}

	public void deleteImage(Integer id) {
		imageRepository.delete(id);
		
	}
}
