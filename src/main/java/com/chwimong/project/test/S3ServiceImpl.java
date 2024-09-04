package com.chwimong.project.test;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    public S3ServiceImpl() {

    	this.s3Client = S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create()) // 환경변수
                .build();
    }

    @Override
    public void listBuckets() {
        try {
            ListBucketsResponse listBucketsResponse = s3Client.listBuckets();
            System.out.println("S3 버킷 목록 ::: ");
            listBucketsResponse.buckets().forEach(bucket -> System.out.println(bucket.name()));
        } catch (Exception e) {
            System.err.println("AWS S3 연결 ㄴ실패");
            e.printStackTrace();
        }
    }
}
