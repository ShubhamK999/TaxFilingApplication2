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
public class EditProfileServiceImpl implements EditProfileService {

	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	EmployerRepository employerRepo;
	@Autowired
	RepresentativeRepository repRepo;
	@Autowired
	AdminRepository adminRepo;

	@Override
	public Customer findCustomer(long id) {
		//return customerRepo.findCustomer(id);
		
		return customerRepo.findById(id).orElse(null);
	}

	@Override
	public int updateCustomer(Customer c) {
		customerRepo.save(c);
		return 1;
	}

	@Override
	public Employer findEmployerByOrg(String newOrgName) {
		return employerRepo.findEmployer(newOrgName);
	}

	@Override
	public Employer findEmployer(Long id) {
		return employerRepo.findById(id).orElse(null);
	}

	@Override
	public int updateEmployer(Employer e) {
		employerRepo.save(e);
		return 1;
	}

	@Override
	public Representative findRepresentative(Long id) {
		return repRepo.findById(id).orElse(null);
	}

	@Override
	public int updateRepresentative(Representative r) {
		repRepo.save(r);
		return 1;
	}

	@Override
	public Admin findAdmin(String id) {
		return adminRepo.findById(id).orElse(null);
	}

	@Override
	public int updateAdmin(Admin a) {
		adminRepo.save(a);
		return 1;
	}
	@Override
	public int removeCustomer(Long id) {
		customerRepo.deleteById(id);
		return 1;
	}

	@Override
	public int removeEmployer(Long id) {
		employerRepo.deleteById(id);
		return 1;
	}

	@Override
	public int removeRepresentative(Long id) {
		repRepo.deleteById(id);	
		return 1;
	}
}
