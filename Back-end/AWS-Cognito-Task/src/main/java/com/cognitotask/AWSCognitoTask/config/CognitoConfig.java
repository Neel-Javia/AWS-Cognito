package com.cognitotask.AWSCognitoTask.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderAsyncClient;

@Configuration
public class CognitoConfig {

    @Value("${cognito.region}")
    private String awsRegion;

    @Value("${cognito.app-client-id}")
    private String clientId;

    @Bean
    public CognitoIdentityProviderAsyncClient cognitoIdentityProviderAsyncClient() {
        return CognitoIdentityProviderAsyncClient.builder()
                .region(Region.of(awsRegion))
                .build();
    }


    //extra
    @Bean
    public AWSCognitoIdentityProvider cognitoIdentityProvider(){
        return AWSCognitoIdentityProviderClientBuilder.standard().withRegion(awsRegion).build();
    }

}
