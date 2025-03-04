import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import * as s3 from 'aws-cdk-lib/aws-s3';
import * as s3n from 'aws-cdk-lib/aws-s3-notifications';
import * as iam from 'aws-cdk-lib/aws-iam';

export class S3LambdaStack extends cdk.Stack {
    constructor(scope: Construct, id: string, props?: cdk.StackProps) {
        super(scope, id, props);

        const bucket = new s3.Bucket(this, 'CsvBucket', {
              removalPolicy: cdk.RemovalPolicy.DESTROY
              });

        const csvProcessorLambda = new lambda.Function(this, 'CsvProcessorLambda', {
            runtime: lambda.Runtime.JAVA_17,
            handler: 'com.example.CsvProcessor::handleRequest',
            memorySize: 512,
            timeout: cdk.Duration.seconds(30),
            code: lambda.Code.fromAsset('../target/s3-lambda-processor-1.0-SNAPSHOT.jar')
        });

        // ✅ Grant permissions to the Lambda to read from the bucket
        bucket.grantRead(csvProcessorLambda);

        bucket.addEventNotification(
              s3.EventType.OBJECT_CREATED,
              new s3n.LambdaDestination(csvProcessorLambda),
              { prefix: 'csv-files/' }
        );
    }
}
