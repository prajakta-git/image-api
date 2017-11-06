package com.imagepro.image;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("/images")
	public List<Image> getAllImages(){
		return imageService.getAllImages();
	}

	@RequestMapping("/images/{id}")
	public Image getImages(@PathVariable Integer id){
		return imageService.getImage(id);
	}
	
	@RequestMapping(method=RequestMethod.POST , value="/images")
	public Integer addImages(@RequestBody Image image){
		return imageService.addImage(image);
	}
	
	@RequestMapping(method=RequestMethod.PUT , value="/images/{id}")
	public void updateImages(@RequestBody Image image , @PathVariable Integer id){
		imageService.updateImage(image);
	}
	
	@RequestMapping(method=RequestMethod.DELETE , value="/images/{id}")
	public void updateImages(@PathVariable Integer id){
		imageService.deleteImage(id);
	}
	
	
}
