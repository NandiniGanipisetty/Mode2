package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.customerdto.AccountDto;
import com.example.demo.dao.AccountDao;
import com.example.demo.model.Account;





@Service
public class AccountService {
	@Autowired
	AccountDao accountDao;
	private int custid=1000;
	ModelMapper mapper =new ModelMapper();
	List<AccountDto>list=new ArrayList<AccountDto>();
	public String addAccount(Account account) {
		account.setCust_id(custid++);
		accountDao.save(account);
		return "account details added";
	}
	public  List<AccountDto>getAllAccount() {
		accountDao.findAll().forEach(account ->convertToDTo(account));
		return list;
	}
	public void convertToDTo(Account account)
	{
	
		list.add(mapper.map(account, AccountDto.class));
	}
	
}
