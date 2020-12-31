package com.taxfiling.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taxfiling.entity.Customer;
import com.taxfiling.entity.Notice;
import com.taxfiling.entity.TaxForm;
import com.taxfiling.service.NoticeHandlerService;

@RestController
public class NoticeHandlerController {
	@Autowired
	private NoticeHandlerService ns;

	@GetMapping("/viewadminnotice/{email_n}")
	public List<Notice> viewadminnotice(@PathVariable("email_n") String email_n) {
		List<Notice> ll = ns.viewadminnotice(email_n);
		List<Notice> ll1 = new ArrayList<Notice>();
		for (Notice n : ll) {
			Notice n1 = new Notice();
			n1.setNoticeBody(n.getNoticeBody());
			n1.setNoticeId(n.getNoticeId());
			ll1.add(n1);
		}
		return ll1;
	}

	@GetMapping("/viewcustomernotice/{customer_id}")
	public List<Notice> viewCustomerNotice(@PathVariable("customer_id") Long id) {
		List<Notice> ll = ns.viewCustomerNotice(id);
		List<Notice> ll1 = new ArrayList<Notice>();
		for (Notice n : ll) {
			Notice n1 = new Notice();
			n1.setNoticeBody(n.getNoticeBody());
			n1.setNoticeId(n.getNoticeId());
			ll1.add(n1);
		}
		return ll1;
	}

	@GetMapping("/viewRepresentativeNotice/{representative_id}")
	public List<Notice> viewRepresentativeNotice(@PathParam("representative_id") Long id) {
		List<Notice> ll = ns.viewRepresentativeNotice(id);
		List<Notice> ll1 = new ArrayList<Notice>();
		for (Notice n : ll) {
			Notice n1 = new Notice();
			n1.setNoticeBody(n.getNoticeBody());
			n1.setNoticeId(n.getNoticeId());
			ll1.add(n1);
		}
		return ll1;
	}

	@PostMapping("/addnotice")
	public String addnotice(@RequestBody Notice n) {
		String str = "Registration unsuccessful";
		
		int i = ns.addnotice(n);
		
		if (i > 0) {
			str = "Notice  is sent";
		}
		Customer c=ns.getCustomerById(n.getCustomer().getCustomerId());
		TaxForm t = ns.getTaxformById(c.getTaxForm().getTaxformId());
		int i1 = 0;
		if (n.getNoticeBody().equals("Your Details are Incorrect .Your Application is Rejected."))
			i1 = ns.updateTaxForm(t.getTaxformId(),"rejected_r");
		else if (n.getNoticeBody().contains("Your Application is verified"))
			i1 = ns.updateTaxForm(t.getTaxformId(), "verified");
		else if (n.getNoticeBody().equals("Your Application is Rejected."))
			i1 = ns.updateTaxForm(t.getTaxformId(), "rejected_a");
		else if (n.getNoticeBody().equals("Your Application is Approved."))
			i1 = ns.updateTaxForm(t.getTaxformId(), "approved");
		System.out.println(i1);
		return str;
	}
}
