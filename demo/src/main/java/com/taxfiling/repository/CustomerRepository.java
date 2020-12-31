package com.taxfiling.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taxfiling.entity.Customer;


@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT c FROM Customer c where c.pan=:pan")
	Customer getCustByPan(String pan);

	@Query("SELECT c FROM Customer c where c.customerId=:customerId and c.password=:password")
	Customer loginCustomer(Long customerId, String password);
}
