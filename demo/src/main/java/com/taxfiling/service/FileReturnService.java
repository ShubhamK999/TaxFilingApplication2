package com.taxfiling.service;

import com.taxfiling.entity.Customer;
import com.taxfiling.entity.TaxForm;

public interface FileReturnService {

	public int fileReturn(TaxForm t);

	public TaxForm getTaxFromByPan(String pan);

	public Customer getCustomerById(Long id);
}
