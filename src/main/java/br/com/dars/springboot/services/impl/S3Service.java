package br.com.dars.springboot.services.impl;

import br.com.dars.springboot.services.exceptions.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            System.out.println("Name: " + fileName);
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            System.out.println("ContentType: " + contentType);
            return uploadFile(inputStream, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("Erro ao converter");
        }

    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {

        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            s3Client.putObject(bucketName, fileName, inputStream, meta);


            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter");
        }
    }


    public URI uploadFileById(MultipartFile multipartFile, Long id) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            System.out.println("Name: " + fileName);
            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            System.out.println("ContentType: " + contentType);
            return uploadFileById(inputStream, fileName, contentType, id);
        } catch (IOException e) {
            throw new FileException("Erro ao converter");
        }
    }

    public URI uploadFileById(InputStream inputStream, String fileName, String contentType, Long id) {

        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            String newBucketName = bucketName + "/" +id;
            s3Client.putObject(newBucketName, fileName, inputStream, meta);


            return s3Client.getUrl(newBucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter");
        }
    }
}
