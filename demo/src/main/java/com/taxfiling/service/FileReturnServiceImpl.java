package com.taxfiling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxfiling.entity.Customer;
import com.taxfiling.entity.TaxForm;
import com.taxfiling.repository.CustomerRepository;
import com.taxfiling.repository.TaxFormRepository;

@Service
public class FileReturnServiceImpl implements FileReturnService {

	@Autowired
	private TaxFormRepository taxformRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public int fileReturn(TaxForm t) {
		return taxformRepo.fileReturn(t);
	}

	@Override
	public TaxForm getTaxFromByPan(String pan) {
		return taxformRepo.getTaxFormByPan(pan);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepo.findById(id).orElse(null);
	}
}
