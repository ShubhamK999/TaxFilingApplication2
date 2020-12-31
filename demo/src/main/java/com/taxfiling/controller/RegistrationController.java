package com.taxfiling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taxfiling.entity.Admin;
import com.taxfiling.entity.Customer;
import com.taxfiling.entity.Employer;
import com.taxfiling.entity.Representative;
import com.taxfiling.service.RegistrationService;

import io.swagger.annotations.ApiOperation;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationService ts;

	@PostMapping("/registerCustomer/{organizationName}")
	@ApiOperation("****if you are not employee put organization name as null****")
	public String registercustomer(@RequestBody Customer c, @PathVariable("organizationName") String orgName) {
		String str = "Registration unsuccessful";
		int i = 0;
		Admin a = ts.findAdmin("admin");
		c.setAdmin_c(a);
		if (c.getIsEmployee() == true) {
			Employer e = ts.findEmployerByOrg(orgName);
			c.setEmployer(e);
			i = ts.registerCustomer(c);
		}

		i = ts.registerCustomer(c);
		if (i > 0) {
			str = "Registration successful";
		}

		return str;

	}

	@PostMapping("/registerEmployer")
	public String registerEmployer(@RequestBody Employer e) {
		String str = "Registration unsuccessful";
		e.setAdmin_e(ts.findAdmin("admin"));
		int i = ts.registerEmployer(e);
		if (i > 0) {
			str = "Registration successful";
		}

		return str;
	}

	@PostMapping("/registerRepresentative")
	public String registerRepresentative(@RequestBody Representative r) {
		String str = "Registration unsuccessful";
		r.setAdmin_r(ts.findAdmin("admin"));
		int i = ts.registerRepresentative(r);
		if (i > 0) {
			str = "Registration successful";
		}

		return str;
	}

}
