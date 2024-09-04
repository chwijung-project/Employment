package com.chwimong.project.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

@Component
public class CrawlingScheduler {

    private static final String BUCKET_NM = "cj-crawling";
    private static final String[] FOLDERS = {"jumpfit/", "wanted/"};
    private final S3Client s3Client;

    public CrawlingScheduler() {
        this.s3Client = S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }
    
//    @Scheduled(cron = "0 0 5 * * MON") // 매주 월 새벽5 (남경님 크롤링 : 매주 월요일 새벽 4시)
    @Scheduled(fixedRate = 60000) // 1m
    public void checkCsvFiles() {
//        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	String currentDate = "2024-09-01";
        System.out.println("업데이트 날짜 출력 ::: " + currentDate);

        for (String folder : FOLDERS) {
            try {
                ListObjectsV2Request request = ListObjectsV2Request.builder()
                        .bucket(BUCKET_NM)
                        .prefix(folder)
                        .build();

                ListObjectsV2Response result = s3Client.listObjectsV2(request);
                List<S3Object> objects = result.contents();

                String currentDateFolder = folder + currentDate + "/";
                System.out.println("접근해야하는 폴더명::: " + currentDateFolder);

                List<S3Object> filteredFiles = objects.stream()
                        .filter(object -> object.key().startsWith(currentDateFolder)
                                && object.key().matches(".+_\\d{4}-\\d{2}-\\d{2}\\.csv$")) // 파일명_yyyy-mm-dd.csv 
                        .toList();

                if (filteredFiles.isEmpty()) {
                    System.out.println(currentDateFolder + " 는 미존재 폴더.");
                    continue;
                }

                filteredFiles.forEach(csvFile -> {
                    System.out.println("업데이트 CSV 파일::: " + csvFile.key());
                    processCsvFile(csvFile.key());
                    printCsvFileContent(csvFile.key());
                });

            } catch (Exception e) {
            	System.out.println("접근 가능 항목 없음");
                e.printStackTrace();
            }
        }
    }

    private void processCsvFile(String key) {
        try {
            System.out.println("MongoDB Insert 로직 실행: " + key);
            // MongoDB에 데이터를 저장하기 위한 로직 추가(당근당근)
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void printCsvFileContent(String key) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(BUCKET_NM)
                    .key(key)
                    .build();

            InputStream inputStream = s3Client.getObject(getObjectRequest);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




