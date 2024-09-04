package com.chwimong.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chwimong.project.config.ChwiMongApplication;

@SpringBootTest(classes = ChwiMongApplication.class)
public class S3ServiceTest {

    @Autowired
    private S3Service s3Service;

    @Test
    public void testListBuckets() {
        // S3 연결 및 버킷 목록 가져오기 테스트
        s3Service.listBuckets();
    }
}
