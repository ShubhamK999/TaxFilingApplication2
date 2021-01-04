package com.taxfiling.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taxfiling.entity.Customer;
import com.taxfiling.entity.TaxForm;
import com.taxfiling.service.AddTaxDetailsService;
import com.taxfiling.service.TaxFilingServiceImpl;

@RestController
public class AddTaxDetailsController {
	@Autowired
	private AddTaxDetailsService ats;
	
	@Autowired
	private TaxFilingServiceImpl tfs;
	
	@PutMapping("/taxDetailsForEmployee")
	public String addTaxDetailsForEmployee(@RequestBody TaxForm objTaxForm) {
		String str = "Taxform details not added";
		LocalDate today = LocalDate.now(); // Today's date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date2 = LocalDate.parse(objTaxForm.getExtraInfo(), formatter);
		Period p = Period.between(date2, today);
		int age = p.getYears();
		
		// Gross Total Income
				double gti = objTaxForm.getTotalIncomeSalary() + objTaxForm.getOtherIncome() + objTaxForm.getInterestIncome()
						+ objTaxForm.getRentalIncome();

				// Section 80C
				double d1;
				if (objTaxForm.getPpf() > 150000)
					d1 = 150000;
				else
					d1 = objTaxForm.getPpf();

				// Section 80D
				double d2;
				if (age > 60) {
					if (objTaxForm.getMedicalInsurance() > 50000)
						d2 = 50000;
					else
						d2 = objTaxForm.getMedicalInsurance();
				} else {
					if (objTaxForm.getMedicalInsurance() > 25000)
						d2 = 25000;
					else
						d2 = objTaxForm.getMedicalInsurance();
				}

				// Section 80CCD NPS+APY
				double d3;
				if (objTaxForm.getNps() > 50000)
					d3 = 50000;
				else
					d3 = objTaxForm.getNps();

				// Section 80TTA
				double d4;
				if (objTaxForm.getSavingsInterest() > 10000)
					d4 = 10000;
				else
					d4 = objTaxForm.getSavingsInterest();

				// Section 80EEA
				double d5;
				if (objTaxForm.getHouseLoan() > 200000)
					d5 = 200000;
				else
					d5 = objTaxForm.getHouseLoan();

				// Total Deductions
				double td = d1 + d2 + d3 + d4 + d5;

				// Total taxable income after deductions
				double ti = gti - td;

				// Tax amount
				double tax1 = 0;
				if (age <= 60) {
					if (ti <= 250000)
						tax1 = 0;
					else if (ti > 250000 && ti <= 500000)
						tax1 = 5 / 100.0 * ti;
					else if (ti > 500000 && ti <= 1000000)
						tax1 = 12500 + 0.2 * ti;
					else if (ti > 1000000)
						tax1 = 112500 + (30 / 100.0 * ti);
				} else if (age > 60 && age <= 80) {
					if (ti <= 300000)
						tax1 = 0;
					else if (ti > 300000 && ti <= 500000)
						tax1 = 5 / 100.0 * ti;
					else if (ti > 500000 && ti <= 1000000)
						tax1 = 10000 + (20 / 100.0 * ti);
					else if (ti > 1000000)
						tax1 = 110000 + (30 / 100.0 * ti);
				} else if (age > 80) {
					if (ti <= 500000)
						tax1 = 0;
					else if (ti > 500000 && ti <= 1000000)
						tax1 = 20 / 100.0 * ti;
					else if (ti > 1000000)
						tax1 = 100000 + (30 / 100.0 * ti);
				}
				objTaxForm.setTds(tax1);
				objTaxForm.setVerifiedStatus("none");
		
		int i = ats.addTaxDetailsForEmployeeService(objTaxForm);
		if (i > 0) {
			str = "Taxform details added successfully";
		}
		return str;
	}

	@PutMapping("/taxDetailsByCustomer")
	public String addTaxDetailsByCustomer(@RequestBody TaxForm objTaxForm) {
		String str = "Taxform details not added";
		Customer c = ats.getCustomerByPan(objTaxForm.getPan());
		LocalDate today = LocalDate.now(); // Today's date
		Period p = Period.between(c.getDateOfBirth(), today);
		int age = p.getYears();

		// Gross Total Income
		double gti = objTaxForm.getTotalIncomeSalary() + objTaxForm.getOtherIncome() + objTaxForm.getInterestIncome()
				+ objTaxForm.getRentalIncome();

		// Section 80C
		double d1;
		if (objTaxForm.getPpf() > 150000)
			d1 = 150000;
		else
			d1 = objTaxForm.getPpf();

		// Section 80D
		double d2;
		if (age > 60) {
			if (objTaxForm.getMedicalInsurance() > 50000)
				d2 = 50000;
			else
				d2 = objTaxForm.getMedicalInsurance();

		} else {
			if (objTaxForm.getMedicalInsurance() > 25000)
				d2 = 25000;
			else
				d2 = objTaxForm.getMedicalInsurance();
		}

		// Section 80CCD NPS+APY
		double d3;
		if (objTaxForm.getNps() > 50000)
			d3 = 50000;
		else
			d3 = objTaxForm.getNps();

		// Section 80TTA
		double d4;
		if (objTaxForm.getSavingsInterest() > 10000)
			d4 = 10000;
		else
			d4 = objTaxForm.getSavingsInterest();

		// Section 80EEA
		double d5;
		if (objTaxForm.getHouseLoan() > 200000)
			d5 = 200000;
		else
			d5 = objTaxForm.getHouseLoan();

		// Total Deductions
		double td = d1 + d2 + d3 + d4 + d5;

		// Total taxable income after deductions
		double ti = gti - td;

		// Tax amount
		double tax1 = 0;
		if (age <= 60) {
			if (ti <= 250000)
				tax1 = 0;
			else if (ti > 250000 && ti <= 500000)
				tax1 = 5 / 100.0 * ti;
			else if (ti > 500000 && ti <= 1000000)
				tax1 = (double) (12500 + 0.2 * ti);
			else if (ti > 1000000)
				tax1 = 112500 + (30 / 100.0 * ti);

		} else if (age > 60 && age <= 80) {
			if (ti <= 300000)
				tax1 = 0;
			else if (ti > 300000 && ti <= 500000)
				tax1 = 5 / 100.0 * ti;
			else if (ti > 500000 && ti <= 1000000)
				tax1 = 10000 + (20 / 100.0 * ti);
			else if (ti > 1000000)
				tax1 = 110000 + (30 / 100.0 * ti);

		} else if (age > 80) {
			if (ti <= 500000)
				tax1 = 0;
			else if (ti > 500000 && ti <= 1000000)
				tax1 = 20 / 100.0 * ti;
			else if (ti > 1000000)
				tax1 = 100000 + (30 / 100.0 * ti);

		}
		objTaxForm.setPayableTax(tax1);
		objTaxForm.setVerifiedStatus("pending");
		int i;
		
		if (c.getIsEmployee())
			i = ats.addTaxDetailsByCustomerService(objTaxForm);
		else
			i = ats.addTaxDetailsByNewCustomerService(objTaxForm);
		
		if (i > 0) {
			str = "Taxform details added successfully";
		}
		return str;
	}
	
	/*@PutMapping("/addtaxDetails/{Total Income},{Other Income},{Income from Interest},{Rental Income},{PPF/ELSS/LIC},{Medical Insurance},{Education Loan},{NPS/APY},{Bank Interest},{Home Loan}")
	public String addTaxDetails(@RequestBody Customer c, 
			@PathVariable("Total Income") double totalIncomeSalary,
			@PathVariable("Other Income") double otherIncome,
			@PathVariable("Income from Interest") double interestIncome,
			@PathVariable("Rental Income") double rentalIncome,
			@PathVariable("PPF/ELSS/LIC") double ppf,
			@PathVariable("Medical Insurance") double medicalInsurance,
			@PathVariable("Education Loan") double educaionLoan,
			@PathVariable("NPS/APY") double nps,
			@PathVariable("Bank Interest") double savingsInterest,
			@PathVariable("Home Loan") double houseLoan) {
		
		TaxForm objTaxForm;
		if (c.getIsEmployee())
			objTaxForm = tfs.getTaxFromByPan(c.getPan());
		else {
			objTaxForm = new TaxForm();
			objTaxForm.setPan(c.getPan());
		}
		
		objTaxForm.setTotalIncomeSalary(totalIncomeSalary);
		objTaxForm.setOtherIncome(otherIncome);
		objTaxForm.setInterestIncome(interestIncome);
		objTaxForm.setRentalIncome(rentalIncome);
		objTaxForm.setPpf(ppf);
		objTaxForm.setMedicalInsurance(medicalInsurance);
		objTaxForm.setEducaionLoan(educaionLoan);
		objTaxForm.setNps(nps);
		objTaxForm.setSavingsInterest(savingsInterest);
		objTaxForm.setHouseLoan(houseLoan);
		
		LocalDate today = LocalDate.now(); // Today's date
		Period p = Period.between(c.getDateOfBirth(), today);
		int age = p.getYears();

		// Gross Total Income
		double gti = objTaxForm.getTotalIncomeSalary() + objTaxForm.getOtherIncome() + objTaxForm.getInterestIncome()
				+ objTaxForm.getRentalIncome();

		// Section 80C
		double d1;
		if (objTaxForm.getPpf() > 150000)
			d1 = 150000;
		else
			d1 = objTaxForm.getPpf();

		// Section 80D
		double d2;
		if (age > 60) {
			if (objTaxForm.getMedicalInsurance() > 50000)
				d2 = 50000;
			else
				d2 = objTaxForm.getMedicalInsurance();

		} else {
			if (objTaxForm.getMedicalInsurance() > 25000)
				d2 = 25000;
			else
				d2 = objTaxForm.getMedicalInsurance();
		}

		// Section 80CCD NPS+APY
		double d3;
		if (objTaxForm.getNps() > 50000)
			d3 = 50000;
		else
			d3 = objTaxForm.getNps();

		// Section 80TTA
		double d4;
		if (objTaxForm.getSavingsInterest() > 10000)
			d4 = 10000;
		else
			d4 = objTaxForm.getSavingsInterest();

		// Section 80EEA
		double d5;
		if (objTaxForm.getHouseLoan() > 200000)
			d5 = 200000;
		else
			d5 = objTaxForm.getHouseLoan();

		// Total Deductions
		double td = d1 + d2 + d3 + d4 + d5;

		// Total taxable income after deductions
		double ti = gti - td;

		// Tax amount
		double tax1 = 0;
		if (age <= 60) {
			if (ti <= 250000)
				tax1 = 0;
			else if (ti > 250000 && ti <= 500000)
				tax1 = 5 / 100.0 * ti;
			else if (ti > 500000 && ti <= 1000000)
				tax1 = (double) (12500 + 0.2 * ti);
			else if (ti > 1000000)
				tax1 = 112500 + (30 / 100.0 * ti);

		} else if (age > 60 && age <= 80) {
			if (ti <= 300000)
				tax1 = 0;
			else if (ti > 300000 && ti <= 500000)
				tax1 = 5 / 100.0 * ti;
			else if (ti > 500000 && ti <= 1000000)
				tax1 = 10000 + (20 / 100.0 * ti);
			else if (ti > 1000000)
				tax1 = 110000 + (30 / 100.0 * ti);

		} else if (age > 80) {
			if (ti <= 500000)
				tax1 = 0;
			else if (ti > 500000 && ti <= 1000000)
				tax1 = 20 / 100.0 * ti;
			else if (ti > 1000000)
				tax1 = 100000 + (30 / 100.0 * ti);

		}
		objTaxForm.setPayableTax(tax1);
		objTaxForm.setVerifiedStatus("pending");
		int i;
		if (c.getIsEmployee())
			i = ats.addTaxDetailsByCustomerService(objTaxForm);
		else
			i = ats.addTaxDetailsByNewCustomerService(objTaxForm);
		String str="";
		if (i > 0)
		 str = "Details added succesfully. Wait till a reprentative sends notice and then you can file returns.";
		else
			str = "An error occured!";
		return str;
		
	}*/

	/*@PutMapping("/taxDetailsForEmployee/{PAN}/{Birthdate(dd-mm-yyyy)}/{Total Income}/{Other Income}/{Income from Interest}/{Rental Income}/{PPF/ELSS/LIC}/{Medical Insurance}/{Education Loan},{NPS/APY},{Home Loan},{Bank Interest}")
	public String addTaxDetailsForEmployee(@PathVariable("PAN") String pan,
			@PathVariable("Birthdate(dd-mm-yyyy)") String extraInfo, 
			@PathVariable("Total Income") double totalIncomeSalary, 
			@PathVariable("Other Income") double otherIncome, 
			@PathVariable("Income from Interest") double interestIncome,
			@PathVariable("Rental Income") double rentalIncome,
			@PathVariable("PPF/ELSS/LIC") double ppf,
			@PathVariable("Medical Insurance") double medicalInsurance,
			@PathVariable("Education Loan") double educaionLoan,
			@PathVariable("NPS/APY") double nps,
			@PathVariable("Home Loan") double houseLoan,
			@PathVariable("Bank Interest") double savingsInterest
			) {
		TaxForm objTaxForm = new TaxForm();
		objTaxForm.setPan(pan);
		objTaxForm.setExtraInfo(extraInfo);
		objTaxForm.setTotalIncomeSalary(totalIncomeSalary);
		objTaxForm.setOtherIncome(otherIncome);
		objTaxForm.setInterestIncome(interestIncome);
		objTaxForm.setRentalIncome(rentalIncome);
		objTaxForm.setPpf(ppf);
		objTaxForm.setMedicalInsurance(medicalInsurance);
		objTaxForm.setEducaionLoan(educaionLoan);
		objTaxForm.setNps(nps);
		objTaxForm.setSavingsInterest(savingsInterest);
		objTaxForm.setHouseLoan(houseLoan);
		
		LocalDate today = LocalDate.now(); // Today's date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date2 = LocalDate.parse(objTaxForm.getExtraInfo(), formatter);
		Period p = Period.between(date2, today);
		int age = p.getYears();
		
		// Gross Total Income
				double gti = objTaxForm.getTotalIncomeSalary() + objTaxForm.getOtherIncome() + objTaxForm.getInterestIncome()
						+ objTaxForm.getRentalIncome();

				// Section 80C
				double d1;
				if (objTaxForm.getPpf() > 150000)
					d1 = 150000;
				else
					d1 = objTaxForm.getPpf();

				// Section 80D
				double d2;
				if (age > 60) {
					if (objTaxForm.getMedicalInsurance() > 50000)
						d2 = 50000;
					else
						d2 = objTaxForm.getMedicalInsurance();
				} else {
					if (objTaxForm.getMedicalInsurance() > 25000)
						d2 = 25000;
					else
						d2 = objTaxForm.getMedicalInsurance();
				}

				// Section 80CCD NPS+APY
				double d3;
				if (objTaxForm.getNps() > 50000)
					d3 = 50000;
				else
					d3 = objTaxForm.getNps();

				// Section 80TTA
				double d4;
				if (objTaxForm.getSavingsInterest() > 10000)
					d4 = 10000;
				else
					d4 = objTaxForm.getSavingsInterest();

				// Section 80EEA
				double d5;
				if (objTaxForm.getHouseLoan() > 200000)
					d5 = 200000;
				else
					d5 = objTaxForm.getHouseLoan();

				// Total Deductions
				double td = d1 + d2 + d3 + d4 + d5;

				// Total taxable income after deductions
				double ti = gti - td;

				// Tax amount
				double tax1 = 0;
				if (age <= 60) {
					if (ti <= 250000)
						tax1 = 0;
					else if (ti > 250000 && ti <= 500000)
						tax1 = 5 / 100.0 * ti;
					else if (ti > 500000 && ti <= 1000000)
						tax1 = 12500 + 0.2 * ti;
					else if (ti > 1000000)
						tax1 = 112500 + (30 / 100.0 * ti);
				} else if (age > 60 && age <= 80) {
					if (ti <= 300000)
						tax1 = 0;
					else if (ti > 300000 && ti <= 500000)
						tax1 = 5 / 100.0 * ti;
					else if (ti > 500000 && ti <= 1000000)
						tax1 = 10000 + (20 / 100.0 * ti);
					else if (ti > 1000000)
						tax1 = 110000 + (30 / 100.0 * ti);
				} else if (age > 80) {
					if (ti <= 500000)
						tax1 = 0;
					else if (ti > 500000 && ti <= 1000000)
						tax1 = 20 / 100.0 * ti;
					else if (ti > 1000000)
						tax1 = 100000 + (30 / 100.0 * ti);
				}
				objTaxForm.setTds(tax1);
				objTaxForm.setVerifiedStatus("none");
		
		System.out.println("********************");
		int i = ats.addTaxDetailsForEmployeeService(objTaxForm);
		String str="";
		if (i > 0)
			str="Tax details of your employee added successfuly.";
		else
			str="An error occured!";
		return str;
	}*/
	
	
	/*@PutMapping("/taxDetailsByNew")
	public String addTaxDetailsByNewCustomer(@RequestBody TaxForm objTaxForm) {
		String str = "Taxform details not added";
		int i = ats.addTaxDetailsByNewCustomerService(objTaxForm);
		if (i > 0) {
			str = "Taxform details added successfully(New Customer)";
		}
		return str;
	}*/
}
