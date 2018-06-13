package org.store.manager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "str_customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String surname;
	private String mobilePhone;
	private String phone;
	private String email;

	public Customer() {
	}

	public Customer(long id,String name, String surname, String mobilePhone,
			String phone, String email) {
		
		this.id=id;
		this.name = name;
		this.surname = surname;
		this.mobilePhone = mobilePhone;
		this.phone = phone;
		this.email = email;
	}

}
