package com.cognitotask.AWSCognitoTask.controller;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClientBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private AWSCognitoIdentityProvider cognitoIdentityProvider;

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");

            // Verify the token against AWS Cognito
            verifyToken(token);

            // If the token is valid, return the Hello World message
            return ResponseEntity.ok("Hello World");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private void verifyToken(String token){
        try {
            AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                    .authFlow(AuthFlowType.USER_SRP_AUTH)
                    .clientId("35spc9ndf7cst2sci814qu9fco")
                    .userPoolId("ap-south-1_V78vKqgFd")
                    .build();

            cognitoIdentityProvider.adminInitiateAuth(new com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest());
        }catch (UserNotFoundException | NotAuthorizedException e){
            throw new RuntimeException("Invalid Token");
        }
    }
}
