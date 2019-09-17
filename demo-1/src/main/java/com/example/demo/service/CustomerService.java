package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.customerdto.CustomerDto;
import com.example.demo.dao.AccountDao;
import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.Transcation;
@Service
public class CustomerService {
	@Autowired
	CustomerDao customerDao;
	@Autowired
	AccountDao accountdao;
	Account account=new Account();
	 static int cust_id= 1000;   
	 long accountno=2000;
	long balance=7000;
	//AccNumberGeneration generate=new AccNumberGeneration();
	ModelMapper mapper =new ModelMapper();
	List<CustomerDto>list=new ArrayList<CustomerDto>();
	public String AddCustomerDetails(Customer customer)
	{
		if(customer.getAge()>21)
		{
			
			customer.setPassword(customer.getPhoneno());
			
			customer.setCustId(cust_id++);
	      customerDao.save(customer);
	      account.setAccountno("SBI"+accountno++);
	      account.setBalance(balance);
	      account.setCust_id(cust_id);
	    accountdao.save(account);   			
	      return "Successfully Registered";
			}
		else
		{
			return "check your age,password,accno";
		}
	}
		public  List<CustomerDto>getAllcustomer() {
		customerDao.findAll().forEach(customer ->convertToDTo(customer));;
		return list;
	}
	public void convertToDTo(Customer customer)
	{
	
		list.add(mapper.map(customer, CustomerDto.class));
	}
	
	
	public void delete(Integer cust_Id) {
		
		customerDao.deleteById(cust_Id);
	}
	public String  update(Integer cust_Id,String email )
	
	{
		Customer customer=customerDao.findById(cust_Id).orElse(null);
		if(null!=customer)
		{
			customer.setEmail(email);
		}
		customerDao.save(customer);
		return"successfully updated";
	}
	public Customer getCustomerById(Integer cust_id1,Customer customer) {
		// TODO Auto-generated method stub
	return	customerDao.findById(cust_id1).orElse(null);
		
	}
	public List<Customer> getAllcustomerByAge( String sortBy)
    {
        Sort sort= Sort.by(sortBy);
 
        List<Customer> list = (List<Customer>)customerDao.findAll();
        return list;
         
        
    }
	/*public List<Transcation> getAllTransaction(Integer pageNo, Integer pageSize, String sortBy)
    {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Transcation> pagedResult = tranDao.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Transcation>();
        }
    }*/


	
	
	

}
