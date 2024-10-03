package com.passgenius.serviceuser;

import com.passgenius.serviceuser.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static com.mongodb.assertions.Assertions.assertTrue;
class ServiceUserApplicationTests2 {


	private JwtUtil jwtUtil;

    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${JWT_KEY}")
    private String key;

	@BeforeEach
	public void setUp() {
		jwtUtil = new JwtUtil();
//		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGenerateToken() {
//		String username = "testUser";
//		String token = jwtUtil.generateToken(username);
//
//		assertNotNull(token);
//		assertTrue(token.contains(username));
	}

}
