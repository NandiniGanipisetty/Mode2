package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customerdto.CustomerDto;
import com.example.demo.model.Customer;
import com.example.demo.model.Transcation;
import com.example.demo.service.CustomerService;


@RestController

public class CustomerController {

	

	@Autowired
CustomerService custservice;

@PostMapping("/customer/add")	
	public String AddCustomerDetails(@RequestBody Customer customer)
	{
	
  String str=custservice.AddCustomerDetails(customer);
return str;
	
	}
   @PostMapping("/customer/all")
	public List<CustomerDto> getAllcustomer()
	{
	 return custservice.getAllcustomer();
	
	}

@PostMapping("/customer/delete/{cust_Id}")
public  void delete(@PathVariable Integer cust_Id)
{
	custservice.delete(cust_Id);
}
@PostMapping("/customer/update")
public String update(@PathVariable Integer cust_Id,@RequestBody String email)
	
{
	return custservice.update(cust_Id, email);
}
@PatchMapping(value="customer/getOne{cust_id1}")
public Customer getCustomerById(@PathVariable("cust_id1") Integer cust_id1,@RequestBody Customer cust)
{
	return custservice.getCustomerById(cust_id1,cust);

}
@PostMapping(value="/customer/age")
public ResponseEntity<List<Customer>> getAllCustomer(
                    @RequestParam(defaultValue = "0") Integer pageNo,
                    @RequestParam(defaultValue = "10") Integer pageSize,
                    @RequestParam(defaultValue = "age") String sortBy)
{
    List<Customer> list = custservice.getAllcustomerByAge(sortBy);

    return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
}


}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

