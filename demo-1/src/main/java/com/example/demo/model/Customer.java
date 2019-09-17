package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="cust")
public class Customer {
	
		String name;
		int age;
		long phoneno;
		String email;
		
		@Id
	Integer custId;
	
long  password;
		
		
		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Customer(String name, int age, long phoneno, String email, Integer custId, long password) {
			super();
			this.name = name;
			this.age = age;
			this.phoneno = phoneno;
			this.email = email;
			this.custId = custId;
			//this.accNo = accNo;
			this.password = password;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public long getPhoneno() {
			return phoneno;
		}
		public void setPhoneno(long phoneno) {
			this.phoneno = phoneno;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Integer getCustId() {
			return custId;
		}
		public void setCustId(Integer custId) {
			this.custId = custId;
		}
		/*public String getAccNo() {
			return accNo;
		}
		public void setAccNo(String accNo) {
			this.accNo = accNo;
		}*/
		public long getPassword() {
			return password;
		}
		public void setPassword(long password) {
			this.password = password;
		}
		
	
	}


