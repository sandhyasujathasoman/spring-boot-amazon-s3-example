package com.springbootdev.amazon.s3.example.aws.controller;

import com.springbootdev.amazon.s3.example.aws.service.AmazonS3ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/")
public class FileHandlerController {

    @Autowired(required=true)
    private AmazonS3ClientService amazonS3ClientService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
    {
    	System.out.println("Inside function");
        this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");
        System.out.println("before return");
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
    	System.out.println("File name is: "+fileName);
        this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }
    @RequestMapping("/")
    public String welcomeMessage() {
    	String name=  System.getenv("BUCKET_NAME");
    	System.out.println("Bucket Name is: "+name);
		return "Welcome to Service Broker Demo." ;
    	
    }
}