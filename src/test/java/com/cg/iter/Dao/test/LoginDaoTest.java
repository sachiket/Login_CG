package com.cg.iter.Dao.test;


import static org.junit.Assert.*;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.iter.Dao.UserDaoImp;
import com.cg.iter.exception.UserCreateException;
import com.cg.iter.exception.userNotFoundException;
import com.cg.iter.main.User;

public class LoginDaoTest {
	private static  UserDaoImp uDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		uDao = new UserDaoImp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void testAddUser() {
		// sucess = false;
		try {
			boolean sucess = uDao.register(new User("test3","9638527415","test1@gmail.com","Test@12345","test3"));
			assertTrue(sucess);
		} catch (UserCreateException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testGetUser() {
		//User user;
		try {
			User user = uDao.getUser("test1");
			assertNotNull(user);
		} catch (userNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUserMail() {
		boolean sucess = uDao.checkUserEmail("test1", "test1@gmail.com");
		assertTrue(sucess);
	}
	
	@Test
	public void testUserPassword() {
		boolean sucess = uDao.checkUserPassword("test1", "test1");
		assertTrue(sucess);
	}
	
	@Test
	public void testUserID() {
		boolean sucess = uDao.checkUserID("test1");
		assertTrue(sucess);
	}
	
	@Test
	public void testGetMap() {
		Map<String, User> map = uDao.getDao();
		assertNotNull(map);
	}




}
