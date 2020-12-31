package com.taxfiling.service;

import java.util.List;

import com.taxfiling.entity.Customer;
import com.taxfiling.entity.Notice;
import com.taxfiling.entity.TaxForm;

public interface NoticeHandlerService {
	public List<Notice> viewadminnotice(String email);

	public List<Notice> viewCustomerNotice(Long id);

	public List<Notice> viewRepresentativeNotice(Long id);

	public int addnotice(Notice n);

	public TaxForm getTaxformById(long taxformId);

	public int updateTaxForm(long taxformId, String status);

	public Customer getCustomerById(long customerId);
}
