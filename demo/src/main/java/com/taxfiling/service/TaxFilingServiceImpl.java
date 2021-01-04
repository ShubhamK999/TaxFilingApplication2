package com.taxfiling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxfiling.entity.Admin;
import com.taxfiling.entity.Customer;
import com.taxfiling.entity.Employer;
import com.taxfiling.entity.Representative;
import com.taxfiling.entity.TaxForm;
import com.taxfiling.repository.AdminRepository;
import com.taxfiling.repository.CustomerRepository;
import com.taxfiling.repository.EmployerRepository;
import com.taxfiling.repository.RepresentativeRepository;
import com.taxfiling.repository.TaxFormRepository;

@Service
public class TaxFilingServiceImpl implements TaxFilngService {

	
	@Autowired
	private CustomerRepository cust;
	
	@Autowired
	private EmployerRepository emp;
	
	@Autowired
	private RepresentativeRepository rep;
	
	@Autowired
	private AdminRepository admin;
	
	@Autowired
	private TaxFormRepository tax;
	
	
	@Override
	public int registerCustomer(Customer c) {
		cust.save(c);
		return 1;
	}

	@Override
	public int registerEmployer(Employer e) {
		emp.save(e);
		return 1;
	}

	@Override
	public int registerRepresentative(Representative r) {
		rep.save(r);
		return 1;
	}
	
	@Override
	public Customer loginCustomer(Long customerId,String password) {
		return cust.loginCustomer(customerId,password);
	}

	@Override
	public Employer loginEmployer(long id, String password) {
		return emp.loginEmployer(id,password);
	}

	@Override
	public Representative loginRepresentative(long id, String password) {
		return rep.loginRepresentative(id,password);
	}

	@Override
	public Admin loginAdmin(String id, String password) {
		return admin.loginAdmin(id,password);
	}

	@Override
	public Customer findCustomer(long id) {
		return cust.findById(id).orElse(null);
		
	}

	@Override
	public int updateCustomer(Customer c) {
		 cust.save(c);
		 return 1;
	}

	@Override
	public Employer findEmployerByOrg(String newOrgName) {
		return emp.findEmployer(newOrgName);
	}

	@Override
	public Employer findEmployer(Long id) {
		return emp.findById(id).orElse(null);
		
	}

	@Override
	public int updateEmployer(Employer e) {
		emp.save(e);
		return 1;
	}

	@Override
	public Representative findRepresentative(Long id) {
		return rep.findById(id).orElse(null);
		
	}

	@Override
	public int updateRepresentative(Representative r) {
		rep.save(r);
		return 1;
	}

	@Override
	public Admin findAdmin(String id) {
		return admin.findById(id).orElse(null);
		
	}

	@Override
	public int updateAdmin(Admin a) {
		admin.save(a);
		return 1;
	}

	@Override
	public int removeCustomer(Long id) {
		cust.deleteById(id);
		return 1;
	}

	@Override
	public int removeEmployer(Long id) {
		emp.deleteById(id);
		return 1;
	}

	@Override
	public int removeRepresentative(Long id) {
		rep.deleteById(id);	
		return 1;
	}

	public TaxForm getTaxFromByPan(String pan) {
		return tax.getTaxFormByPan(pan);
		
	}
}