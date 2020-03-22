package com.carefirst.auth.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.carefirst.auth.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthenticationRepositoryTest {

	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	AuthenticationRepository authenticationRepository;
	
	
	@Test
	public void testFindByEmailId() {
		User user = new User();
		user.setId(1l);
		user.setEmailId("manikandan.prabhu@carefirst.com");
		user.setPassword("India123");
		User userResponse = entityManager.merge(user);
		assertEquals(userResponse.getEmailId(), "manikandan.prabhu@carefirst.com");
	}
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setId(1l);
		user.setEmailId("manikandan.prabhu@carefirst.com");
		user.setPassword("India123");
		entityManager.merge(user);
		assertEquals("manikandan.prabhu@carefirst.com", authenticationRepository.findById(1L).get().getEmailId());
	}
	
	
}
