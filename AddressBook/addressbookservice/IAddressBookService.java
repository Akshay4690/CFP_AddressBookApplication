package com.addressbook.addressbookservice;

import java.util.List;

import com.addressbook.addressbookdto.AddressBookModelDTO;
import com.addressbook.addressbookentity.AddressBookModel;

import jakarta.validation.Valid;

public interface IAddressBookService {

	AddressBookModel postData(AddressBookModelDTO model);

	List<AddressBookModel> getAllUser();

	AddressBookModel getUserById(int id);

	String deletUser(int id);

	AddressBookModel updateUser(int id, AddressBookModelDTO model);

	AddressBookModel getByEmail(String email);

	AddressBookModel getUserByToken(String token);

	AddressBookModel updateToken(String token, @Valid AddressBookModelDTO model);


}
