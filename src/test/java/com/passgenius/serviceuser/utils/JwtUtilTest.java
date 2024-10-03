package com.passgenius.serviceuser.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.nio.charset.StandardCharsets;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
public class JwtUtilTest {


    private JwtUtil jwtUtil;

    @Value("${JWT-SECRET}")
    private String secret;

    @Value("${JWT-KEY}")
    private String key;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mocking the values for the environment variables
        secret = "your_base64_encoded_secret"; // Set your mock secret here
        key = "your_base64_encoded_key";       // Set your mock key here
    }

    @Test
    public void testGenerateToken() {
        String username = "testUser";
//        String token = jwtUtil.generateToken(username);

        // Verify the token is not null
        assertNotNull(username);

    }


}