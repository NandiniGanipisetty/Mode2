package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transaction;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.AccountNoNotFoundException;
import com.example.demo.customerdto.AccountDto;
import com.example.demo.customerdto.CustomerDto;
import com.example.demo.customerdto.TransactionDto;
import com.example.demo.dao.AccountDao;
import com.example.demo.dao.TranscationDao;
import com.example.demo.model.Account;
import com.example.demo.model.Transcation;
import org.springframework.data.domain.Sort;

@Service
public class TranscationService {
	@Autowired
	TranscationDao tranDao;
	@Autowired
	AccountDao accountdao;
	Account account = new Account();
	Transcation transcation = new Transcation();
	TransactionDto transDto = new TransactionDto();
	AccountDto accDto = new AccountDto();
	int trans_id = 1;
	ModelMapper mapper = new ModelMapper();
	List<TransactionDto> list = new ArrayList<TransactionDto>();

	public long checkbalance(Account account) {

		return account.getBalance();
	}

	public String debit(Transcation transcation2) {

		Account account = accountdao.findById(transcation2.getCust_id()).orElse(null);
		long balance = account.getBalance();
		transcation2.setTrans_id(trans_id++);
		if ((transcation2.getAmount() <= balance)) {
			balance -= transcation2.getAmount();
			transcation2.setCust_id(account.getCust_id());
			transcation2.setTransactiontype("debit");
			// transcation2.setAccountno(account.getAccountno());
			transcation2.setDescription("debited from:" + account.getAccountno() + " and credited to account"+ transcation2.getAccountno());
			transcation2.setAccountno(account.getAccountno());
			account.setBalance(balance);
			System.out.println(account.getBalance());
			tranDao.save(transcation2);

			System.out.println(transcation2);
			accountdao.save(account);
			System.out.println("debit");
			return "debited";
		} else {
			throw new AccountNoNotFoundException("insufficient balance ");

		}
	}

	public String credit(Transcation transactions, String accNo) {
		System.out.println("inside credit method");
		List<Account> accList = (List<Account>) accountdao.findAll();
		List<Account> account1 = accList.stream().filter(x -> x.getAccountno().equals(accNo))
				.collect(Collectors.toList());
		long balance1 = account1.get(0).getBalance();
		int i = transactions.getCust_id();
		int j = account1.get(0).getCust_id();

		if (i != j) {
			if (transactions.getAmount() <= balance1) {
				System.out.println("other account");
				debit(transactions);
				balance1 += transactions.getAmount();
				Transcation transactions1 = new Transcation();
				transactions1.setTransactiontype("credit");
				transactions1.setCust_id(account1.get(0).getCust_id());
				transactions1.setAccountno(account1.get(0).getAccountno());
				transactions1.setDescription("credited to :" + account1.get(0).getAccountno());
				transactions1.setAmount(transactions.getAmount());
				account1.get(0).setBalance(balance1);
				tranDao.save(transactions1);
				accountdao.save(account1.get(0));
				return "credited";
			} else {
				 throw  new AccountNoNotFoundException("insufficientBalance");
			}
		} else {
			balance1 += transactions.getAmount();
			Transcation transcation1 = new Transcation();
			transcation1.setTransactiontype("credit");
			transcation1.setCust_id(account1.get(0).getCust_id());
			transcation1.setAccountno(account1.get(0).getAccountno());
			transcation1.setDescription("credited to :" + account1.get(0).getAccountno());
			transcation1.setAmount(transcation.getAmount());
			account1.get(0).setBalance(balance1);
			tranDao.save(transcation1);
			accountdao.save(account1.get(0));
			System.out.println("credited");
			return "credited";
		}
	}


	public List<TransactionDto> getAllTransaction() {
		tranDao.findAll().forEach(transaction -> convertToDto(transaction));
		return list;
	}

	public void convertToDto(Transcation transaction) {
		list.add(mapper.map(transaction, TransactionDto.class));
	}

	public List<Transcation> getAllTransaction(Integer pageNo, Integer pageSize, String sortBy) {
		PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Transcation> pagedResult = tranDao.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Transcation>();
		}
	}

}
