package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class RestConsumer {

	public static void main(String[] args) {
		
		String imgPath = "test.jpg";
		String imgid = POST(imgPath);
		System.out.println("=========Id======"+ imgid );
		
		//uncomment GET() method to test get request to image api.
		//GET(imgid);
		
		
	}
	
	public static String POST(String imgPath){
		
		System.out.println("===encoding image====");
		String imgEncoded = imageEncode(imgPath);
		
		String imgid="";
		
		try{
			URL url = new URL("http://localhost:8080/images");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"imageId\":1,\"name\":\"" +imgPath+ "\", \"imgDesc\":\""+imgEncoded+"\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println("Image saved with Image id:"+output);
				imgid = output;
			}

			conn.disconnect();
			
			
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return imgid;
	}
	
	public static void GET(String imgid){
		
		/*System.out.println("=====Decode image ====");
		imageDecoder(imgEncoded, "decodedimg.jpg");*/
		
		try{
			URL url = new URL("http://localhost:8080/images/"+imgid);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
						
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println("Image saved with Image id:"+output);
			}

			conn.disconnect();
			
			
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static String imageEncode(String filePath){
		String img_base64encoded ="";
		
		File img_file = new File(filePath);
		try(FileInputStream fis = new FileInputStream(img_file)){
			
			byte imgByte[] = new byte[(int)img_file.length()];
			fis.read(imgByte);
			
			img_base64encoded = Base64.getEncoder().encodeToString(imgByte);
			
		}catch(FileNotFoundException e) {
			System.out.println("Image not found" + e);			
		}
		catch(IOException io){
			System.out.println("Exception reading Image " + io);
		}		
		
		return img_base64encoded;
		
	}
	
	public static void imageDecoder(String base64Image, String pathFile) {
		
		try (FileOutputStream fos = new FileOutputStream(pathFile)) {
			byte[] imageByte = Base64.getDecoder().decode(base64Image);
			fos.write(imageByte);
			
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException io) {
			System.out.println("Exception reading Image " + io);
		}
	}
	

}
