package com.taxfiling.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxfiling.entity.Customer;
import com.taxfiling.entity.Notice;
import com.taxfiling.entity.TaxForm;
import com.taxfiling.repository.CustomerRepository;
import com.taxfiling.repository.NoticeRepository;
import com.taxfiling.repository.TaxFormRepository;

@Service
public class NoticeHandlerServiceImpl implements NoticeHandlerService{
	@Autowired
	private NoticeRepository noticeRepo;
	
	@Autowired
	private TaxFormRepository taxformRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	@Override
	public List<Notice> viewadminnotice(String email) {
		
		return noticeRepo.viewadminnotice(email);
	}

	@Override
	public List<Notice> viewCustomerNotice(Long id) {
		return noticeRepo.viewCustomerNotice(id);
	}

	@Override
	public List<Notice> viewRepresentativeNotice(Long id) {
		return noticeRepo.viewRepresentativeNotice(id);
	}

	@Override
	public int addnotice(Notice n) {
		noticeRepo.save(n);
		return 1;
	}

	@Override
	public TaxForm getTaxformById(long taxformId) {
		return taxformRepo.findById(taxformId).orElse(null);
	}

	@Override
	public int updateTaxForm(long taxformId, String status) {
		return taxformRepo.updateTaxForm(taxformId,status);
	}

	@Override
	public Customer getCustomerById(long customerId) {
		return customerRepo.findById(customerId).orElse(null);
	}
}
