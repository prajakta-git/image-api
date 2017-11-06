package com.imagepro.image;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image {
	
	@Id	
	private Integer imageId;
	private String name;
	
	@Lob
	private byte[] imgDesc;

	public Image(){
		
	}
	
	public Image(Integer imageId,String name,byte[] desc){
		this.imageId = imageId;
		this.name = name;
		this.imgDesc = desc;		
	}

	public Integer getimageId() {
		return imageId;
	}

	public void setimageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getimgDesc() {
		return imgDesc;
	}

	public void setimgDesc(byte[] desc) {
		this.imgDesc = desc;
	}

	
	
}
