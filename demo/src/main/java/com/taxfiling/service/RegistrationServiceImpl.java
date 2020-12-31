package com.taxfiling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxfiling.entity.Admin;
import com.taxfiling.entity.Customer;
import com.taxfiling.entity.Employer;
import com.taxfiling.entity.Representative;
import com.taxfiling.repository.AdminRepository;
import com.taxfiling.repository.CustomerRepository;
import com.taxfiling.repository.EmployerRepository;
import com.taxfiling.repository.RepresentativeRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	EmployerRepository employerRepo;
	@Autowired
	RepresentativeRepository repRepo;
	@Autowired
	AdminRepository adminRepo;

	public int registerCustomer(Customer c) {
		System.out.println("cname:" + c.getName());
		customerRepo.save(c);
		return 1;
	}

	public int registerEmployer(Employer e) {
		employerRepo.save(e);
		return 1;
	}

	public int registerRepresentative(Representative r) {
		repRepo.save(r);
		return 1;
	}

	public int registerAdmin(Admin a) {
		adminRepo.save(a);
		return 1;
	}

	@Override
	public Employer findEmployerByOrg(String newOrgName) {
		return employerRepo.findEmployer(newOrgName);
	}

	@Override
	public Admin findAdmin(String id) {
		return adminRepo.findById(id).orElse(null);
	}
}
