Project Summary: AWS S3 + Lambda CSV Processor using AWS CDK

📌 Overview

This project automates handling CSV file uploads in Amazon S3 and processing them using an AWS Lambda function written in Java. The infrastructure is provisioned using AWS CDK (TypeScript).

When a CSV file is uploaded to a designated S3 folder (csv-files/), an event triggers the Lambda function, which extracts and logs the column headers from the file.

🚀 Architecture

AWS S3 Bucket

Stores CSV files.
Triggers Lambda when a new file is uploaded to csv-files/.

AWS Lambda (Java 17)

Reads the uploaded CSV file.
Extracts column headers.
Logs the extracted headers in CloudWatch.
AWS IAM Permissions

Lambda has s3:GetObject permission to read CSV files.
AWS CDK (TypeScript)

Deploys and manages the S3 bucket, Lambda function, IAM roles, and event triggers.

🛠️ Technologies Used

AWS CDK (TypeScript)
AWS S3 (Storage & Event Trigger)
AWS Lambda (Java 17)
Amazon CloudWatch (Logging & Monitoring)
IAM Roles & Policies (Security & Permissions)
Maven (Building Java Lambda)


📂 Folder Structure

# AWS S3 Lambda CSV Processor

## Project Structure

```text
aws-s3-lambda-csv-processor/
│
├── cdk/                                  # CDK Infrastructure (TypeScript)
│   ├── bin/
│   │    ├── cdk-app.ts                    # CDK Entry Point
│   ├── lib/
│   │    ├── lambda-stack.ts               # CDK Stack defining S3, Lambda, and event trigger
│   ├── cdk.json                           # CDK configuration
│   ├── tsconfig.json                      # TypeScript configuration
│   ├── package.json                       # Project dependencies
│
├── lambda/                               # Java Lambda Function
│   ├── CsvProcessorLambda/
│   │   ├── src/main/java/com/example/
│   │   │   ├── CsvProcessor.java          # Java Lambda function that reads CSV headers
│   │   ├── pom.xml                        # Maven configuration for building the Lambda JAR
│
├── target/                               # Output directory for packaged Lambda JAR
│   ├── s3-lambda-processor-1.0-SNAPSHOT.jar  # Packaged Lambda JAR ready for deployment

```

🛠️ Deployment Steps

Build the Java Lambda
bash
Copy
Edit
mvn clean package
Deploy with AWS CDK
bash
Copy
Edit
npx cdk deploy
Upload a CSV File to S3
bash
Copy
Edit

aws s3 cp test-file.csv s3://<your-bucket-name>/csv-files/

Check Lambda Logs in CloudWatch

📌 Key Features

✅ Automatic Processing: CSV files uploaded to S3 are automatically processed.

✅ Serverless & Scalable: Uses AWS Lambda for efficient event-driven processing.

✅ AWS CDK Infrastructure as Code (IaC): Enables easy deployment & management.

✅ Java-Based Lambda: Uses AWS SDK to fetch & read files dynamically.

✅ CloudWatch Logging: Logs extracted CSV headers for monitoring & debugging.

🔥 Next Steps / Enhancements

Store extracted headers in DynamoDB for further processing.
Extend Lambda to process entire CSV contents instead of just headers.
Implement S3 lifecycle policies to clean up old CSV files automatically.

🎯 Conclusion

This project provides a fully automated and scalable solution to process CSV files in AWS using S3, Lambda (Java), and AWS CDK. 
It demonstrates a serverless, event-driven architecture for real-world data processing.

<img width="796" alt="image" src="https://github.com/user-attachments/assets/7b0d0cf9-44bd-4768-a560-8129bc45aa45" />

