package com.taxfiling.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.taxfiling.TaxFilingApplication;
import com.taxfiling.controller.RegistrationController;
import com.taxfiling.entity.Admin;

import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaxFilingApplication.class)
//@DataJpaTest
//@ContextConfiguration(classes = TaxFilingApplication.class)
//@WebMvcTest(value = AdminRepository.class)
 class AdminRepositoryTest {
	
	@Autowired
	private AdminRepository ar;
	
	//@Autowired
	// private TestEntityManager testEntityManager;
	
	
	@Test
	 void getloginAdmin() {
		/*Admin a1 = new Admin();
		a1.setEmail("ssk@gmail.com");
		a1.setPassword("sk1234");
		Admin a2 = testEntityManager.persist(a1);*/
		Admin a3 = ar.loginAdmin("sk@cg.com", "sk123");
		//System.out.println(a3.getEmail());
		//assert a3.getEmail().equals("sk@cg.com") : "Test Failed";
		Assert.assertEquals(a3.getEmail(), "sk@cg.com");
	}

	/*@Test
	 void getloginAdminTest() {
		Admin admin = new Admin();
		admin.setEmail("sk@cg.com");
		admin.setPassword("sk123");
		Mockito.when(ar.loginAdmin(Mockito.anyString(), Mockito.anyString())).thenReturn(admin);
		//assertEquals(ar.loginAdmin("sk@cg.com", "sk123"), admin);
		assert admin.getPassword().equals("sk123") : "Test Failed";
	}*/

}
