package com.springbootdev.amazon.s3.example.aws.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Configuration
public class AmazonS3Config {

	@Value("${S3_AWS_ACCESS_KEY_ID}")
	private String awsKeyId;

	@Value("${S3_AWS_SECRET_ACCESS_KEY}")
	private String awsKeySecret;

	@Value("${aws.region}")
	private String awsRegion;

	@Value("${BUCKET_NAME}")
	private String awsS3AudioBucket;

	// private String awsKeyId = System.getenv("S3_AWS_ACCESS_KEY_ID"); private
	// String awsKeySecret = System.getenv("S3_AWS_SECRET_ACCESS_KEY");
	// private String awsRegion = System.getenv("aws.region");
	// private String awsS3AudioBucket = System.getenv("BUCKET_NAME");

	@Bean(name = "awsKeyId")
	public String getAWSKeyId() {
		return awsKeyId;
	}

	@Bean(name = "awsKeySecret")
	public String getAWSKeySecret() {
		return awsKeySecret;
	}

	@Bean(name = "awsRegion")
	public Region getAWSPollyRegion() {
		return Region.getRegion(Regions.fromName(awsRegion));
	}

	@Bean(name = "awsCredentialsProvider")
	public AWSCredentialsProvider getAWSCredentials() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.awsKeyId, this.awsKeySecret);
		return new AWSStaticCredentialsProvider(awsCredentials);
	}

	@Bean(name = "awsS3AudioBucket")
	public String getAWSS3AudioBucket() {
		return awsS3AudioBucket;
	}
	
	public void printConfigs() {
		System.out.println(" Bucket Name = " + awsS3AudioBucket);

	}
}