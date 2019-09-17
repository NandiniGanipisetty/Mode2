

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Beneficinary")
public class BeneficinaryAccount {
@Id
private String bene_accno;
private int cust_id;
private String ifscCode;
public String getBene_accno() {
	return bene_accno;
}
public void setBene_accno(String bene_accno) {
	this.bene_accno = bene_accno;
}
public int getCust_id() {
	return cust_id;
}
public void setCust_id(int cust_id) {
	this.cust_id = cust_id;
}
public String getIfscCode() {
	return ifscCode;
}
public void setIfscCode(String ifscCode) {
	this.ifscCode = ifscCode;
}

}
