package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customerdto.AccountDto;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;

	@PostMapping("/account/add")
	public String addTransactionDetails(@RequestBody Account account) {
		String str = accountService.addAccount(account);
		return str;
	}
	 @PostMapping("/account/all")
		public List<AccountDto> getAllAccount()
		{
		 return accountService.getAllAccount();
		
		}

}
