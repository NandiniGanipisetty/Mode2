package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BeneficinaryAccount;


@Repository
public interface BeneficiaryDao extends CrudRepository<BeneficinaryAccount,String>{
	BeneficinaryAccount findByIfscCode(String bene_accno);
	//BeneficinaryAccount findByBeneAccandIfscCode(String ifscCode,String bene_accno);
}
