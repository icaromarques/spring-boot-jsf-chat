package br.com.icaro.chat.controller;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.icaro.chat.ChatApplication;
import br.com.icaro.chat.ChatApplicationTests;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ChatApplication.class)
@WebAppConfiguration
public class ChatControllerTest extends ChatApplicationTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBadWordsValidation() {
		
	}
	
	@Test
	public void testSendMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendPrivate() {
		fail("Not yet implemented");
	}

	@Test
	public void testRefreshUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUsers() {
		fail("Not yet implemented");
	}

}
