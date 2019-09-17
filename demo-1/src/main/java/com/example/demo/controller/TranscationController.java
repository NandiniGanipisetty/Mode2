package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.customerdto.AccountDto;
import com.example.demo.customerdto.TransactionDto;
import com.example.demo.model.Account;
import com.example.demo.model.Transcation;
import com.example.demo.service.TranscationService;
@RestController
public class TranscationController {
	@Autowired
	TranscationService transervice;
	AccountDto account = new AccountDto();
	Account acc=new Account();
	TransactionDto transDto=new TransactionDto();
	Transcation trans=new Transcation();
	@PostMapping("/transcation/checkbalance")
	public long checkbalance(@RequestBody Transcation transcation) {
		return  transervice.checkbalance(acc);
		
	}

	@RequestMapping(value = "/transaction/debit/dd", method = RequestMethod.POST)
	
	public String transactionDebit(@RequestBody Transcation transcation) {	
			return transervice.debit(transcation);
		
	}

	@RequestMapping(value = "/transaction/credit/{accNo}", method = RequestMethod.POST)
	public String transactionCredit(@RequestBody Transcation transaction, @PathVariable String accNo) {
		System.out.println("in credit controller");
		return transervice.credit(transaction, accNo);
	}
	 @PostMapping("/transaction/all")
		public List<TransactionDto> getAllAccount()
		{
		 return transervice.getAllTransaction();
		
		}
	    @PostMapping(value="/transact")
	    public ResponseEntity<List<Transcation>> getAllTransaction(
	                        @RequestParam(defaultValue = "0") Integer pageNo,
	                        @RequestParam(defaultValue = "10") Integer pageSize,
	                        @RequestParam(defaultValue = "amount") String sortBy)
	    {
	        List<Transcation> list = transervice.getAllTransaction(pageNo, pageSize, sortBy);
	 
	        return new ResponseEntity<List<Transcation>>(list, new HttpHeaders(), HttpStatus.OK);
	    }


}

