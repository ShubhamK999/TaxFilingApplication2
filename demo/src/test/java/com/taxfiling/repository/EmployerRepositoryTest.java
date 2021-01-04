package com.taxfiling.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.taxfiling.TaxFilingApplication;
import com.taxfiling.entity.Employer;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaxFilingApplication.class)
public class EmployerRepositoryTest {
	
	@Autowired
	private EmployerRepository er;

	@Test
	public void loginEmployer() {
		Employer e1 = er.loginEmployer(1, "qwe");
		//assert e1.getOrganization().equals("CG") : "Test Failed";
		Assert.assertEquals(e1.getOrganization(), "CG");
		
	}
	
	@Test
	public void findEmployer() {
		Employer e2 = er.findEmployer("CG");
		//assert e2.getEmail().equals("abc@gmail.com"): "Test Failed";
		Assert.assertEquals(e2.getEmail(), "abc@gmail.com");
	}

}
