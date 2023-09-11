//package com.cognitotask.AWSCognitoTask.service;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AccessToken;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import java.util.Collection;
//import java.util.Map;
//
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final JwtDecoder jwtDecoder;
//
//    public CustomOAuth2UserService(JwtDecoder jwtDecoder) {
//        Assert.notNull(jwtDecoder, "jwtDecoder cannot be null");
//        this.jwtDecoder = jwtDecoder;
//    }
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        OAuth2AccessToken accessToken = userRequest.getAccessToken();
//
//        Map<String, Object> claims = jwtDecoder.decode(accessToken.getTokenValue()).getClaims();
//
//        // Extract user attributes from claims
//        String username = claims.get("sub").toString();
//
//        String email = claims.get("email").toString();
//
//        // Create a custom OAuth2User with authorities
//        Collection<? extends GrantedAuthority> authorities = getUserAuthorities(registrationId, claims);
//        OAuth2User oauth2User = new CustomOAuth2User(username, email, authorities);
//
//        return oauth2User;
//
//        return null;
//    }
//}
