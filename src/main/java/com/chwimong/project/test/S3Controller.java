package com.chwimong.project.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/s3/buckets")
    public void listBuckets() {
        s3Service.listBuckets();  // S3 서비스에서 버킷 목록을 가져옵니다.
    }
}
