package com.example.uploadfilebinary.storage;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    @Value("${app.firebase-bucket}")
    private String bucket;

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/java/com/example/uploadfilebinary/storage/upload-file-from-java.json");

            FirebaseOptions options =  FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(bucket)
                    .build();
           return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.out.println(e.getMessage());
           return FirebaseApp.initializeApp();
        }
    }

    @Bean
    public Storage storage() throws IOException {
        String projectId = FirebaseApp.getInstance().getOptions().getProjectId();
        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .build();
        return storageOptions.getService();
    }
//    @Bean
//    public Bucket firebaseBucket() throws IOException {
//        String bucketName = FirebaseApp.getInstance().getOptions().getStorageBucket();
//        return storage().get(bucketName);
//    }
}
