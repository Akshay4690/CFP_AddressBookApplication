package com.addressbook.addressbookentity;

import com.addressbook.addressbookdto.AddressBookModelDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AddressBookModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int personId;
	private String name;
	private String city;
	private String state;
	private int zip;
	private String email;
	private long phoneNumber;
	
	public AddressBookModel(AddressBookModelDTO model) {
		this.name = model.getName();
		this.city = model.getCity();
		this.state = model.getState();
		this.zip = model.getZip();
		this.email = model.getEmail();
		this.phoneNumber = model.getPhoneNumber();
	}
	

}
