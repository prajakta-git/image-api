# image-api
This is an image processing microservice which can be consumed programmatically. As of now it can be used to save and retrieve an image file. This service can be further modified to add more image processing services.
# Application Set-up on local machine
1.	Down load the jar file.
2.	Run the jar file using command java -jar jar_file_path.
3.	The service will be up and running.
4.	The sample Rest consumer program can be used to test the service.
5.	The Rest consumer program reads sample image from a path which needs to be updated based on your image location.
6.	It encodes the image to base64 and makes a REST post call to save the image and return image ID, which can be used to retrieve the image again.

