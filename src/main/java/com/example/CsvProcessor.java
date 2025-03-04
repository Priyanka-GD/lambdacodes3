package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CsvProcessor implements RequestHandler<S3Event, List<String>> {

    @Override
    public List<String> handleRequest(S3Event event, Context context) {
        context.getLogger().log("Starting CSV Processor Lambda...\n");

        if (event.getRecords().isEmpty()) {
            context.getLogger().log("No records found in event.\n");
            return List.of();
        }

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

        String bucket = event.getRecords().get(0).getS3().getBucket().getName();
        String key = event.getRecords().get(0).getS3().getObject().getKey();

        context.getLogger().log("Bucket: " + bucket + "\n");
        context.getLogger().log("Key: " + key + "\n");

        try (S3Object s3Object = s3Client.getObject(bucket, key);
             BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()))) {

            context.getLogger().log("Successfully opened S3 object stream.\n");

            // Read and log the header
            String headerLine = reader.readLine();
            context.getLogger().log("Read CSV header: " + headerLine + "\n");

            List<String> headers = Arrays.asList(headerLine.split(","));
            context.getLogger().log("Extracted headers: " + headers + "\n");

            context.getLogger().log("CSV Processing completed successfully.\n");

            return headers;

        } catch (Exception e) {
            context.getLogger().log("Error processing file: " + e.getMessage() + "\n");
            e.printStackTrace();
            return List.of();
        }
    }
}
