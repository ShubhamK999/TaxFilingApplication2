package com.taxfiling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxfiling.entity.Customer;
import com.taxfiling.entity.TaxForm;
import com.taxfiling.service.FileReturnService;

import io.swagger.annotations.ApiOperation;

@RestController
public class FileReturnController {
	@Autowired
	private FileReturnService fileReturnService;

	@PostMapping("/fileReturn/{customerId}")
	@ApiOperation("Enter the customer Id who is filing returns")
	public String fileReturn(@PathVariable("customerId") Long id) {
		String str = "";
		System.out.println("id=" + id);
		Customer c = fileReturnService.getCustomerById(id);
		System.out.println("ccpan:" + c.getPan());
		TaxForm t = fileReturnService.getTaxFromByPan(c.getPan());
		if (c.getTaxForm() != null) {
			if (t.getVerifiedStatus().equals("pending")) {
				str = "Your taxForm is yet to be verified by representative. Check after some time.";
			} else if (t.getVerifiedStatus().equals("verified")) {
				int i = fileReturnService.fileReturn(t);
				if (i > 0)
					str = "File has been successfuly returned. Wait till admin approves the same.";
				else
					str = "An error occurred!";
			} else if (t.getVerifiedStatus().equals("approved")) {
				str = "Your file return is already approved.";
			} else if (t.getVerifiedStatus().equals("none")) {
				str = "Add tax details first!";
			} else if (t.getVerifiedStatus().equals("approvePending")) {
				str = "Your file return is yet to be approved by admin.";
			} else if (t.getVerifiedStatus().equals("rejected_r")) {
				str = "Your file return is rejected by representative.";
			} else if (t.getVerifiedStatus().equals("rejected_a")) {
				str = "Your file return is rejected by admin.";
			}
		} else {
			str = "Add tax details first!";
		}
		return str;
	}
}
