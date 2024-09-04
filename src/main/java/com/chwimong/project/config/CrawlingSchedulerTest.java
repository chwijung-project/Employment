//package com.chwimong.project.config;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.GetObjectRequest;
//import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
//import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
//import software.amazon.awssdk.services.s3.model.S3Object;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
//
//@Component
//public class CrawlingSchedulerTest {
//
//    private static final String BUCKET_NM = "cj-crawling";
//    private static final String FOLDER = "wanted/2024-09-01/"; // 임시
//    private final S3Client s3Client;
//
//    public CrawlingSchedulerTest() {
//        this.s3Client = S3Client.builder()
//                .region(Region.AP_NORTHEAST_2)
//                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
//                .build();
//    }
//
//    @Scheduled(fixedRate = 60000) // 1분
//    public void checkCsvFiles() {
//        try {
//            ListObjectsV2Request request = ListObjectsV2Request.builder()
//                    .bucket(BUCKET_NM)
//                    .prefix(FOLDER)
//                    .build();
//
//            ListObjectsV2Response result = s3Client.listObjectsV2(request);
//            List<S3Object> objects = result.contents();
//
//            if (objects.isEmpty()) {
//                return;
//            }
//
//            objects.stream()
//                    .filter(object -> object.key().matches(".*/.+_\\d{4}-\\d{2}-\\d{2}\\.csv$")) // (파일명)_yyyy-mm-dd.csv
//                    .forEach(csvFile -> {
//                        System.out.println("선택된 CSV 파일: " + csvFile.key());
////                        printCsvFileContent(csvFile.key()); // csv확인완료
//                    });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void printCsvFileContent(String key) {
//        try {
//            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                    .bucket(BUCKET_NM)
//                    .key(key)
//                    .build();
//
//            InputStream inputStream = s3Client.getObject(getObjectRequest);
//
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
