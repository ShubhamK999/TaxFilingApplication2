package com.taxfiling.service;

import com.taxfiling.entity.Admin;
import com.taxfiling.entity.Customer;
import com.taxfiling.entity.Employer;
import com.taxfiling.entity.Representative;

public interface TaxFilngService {

	int registerCustomer(Customer c);

	int registerEmployer(Employer e);

	Customer loginCustomer(Long customerId, String password);

	int registerRepresentative(Representative r);

	Employer loginEmployer(long id, String password);

	Representative loginRepresentative(long id, String password);

	Admin loginAdmin(String id, String password);

	Customer findCustomer(long id);

	int updateCustomer(Customer c);

	Employer findEmployerByOrg(String newOrgName);

	Employer findEmployer(Long id);

	int updateEmployer(Employer e);

	Representative findRepresentative(Long id);

	int updateRepresentative(Representative r);

	Admin findAdmin(String id);

	int updateAdmin(Admin a);

	int removeCustomer(Long id);

	int removeEmployer(Long id);

	int removeRepresentative(Long id);

}
