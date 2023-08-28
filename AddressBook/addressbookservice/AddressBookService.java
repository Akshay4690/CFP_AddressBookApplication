package com.addressbook.addressbookservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressbook.addressbookdto.AddressBookModelDTO;
import com.addressbook.addressbookentity.AddressBookModel;
import com.addressbook.addressbookrepository.AddressBookRespository;
import com.addressbook.excepition.AddressBookException;
import com.addressbook.util.AddressBookTokenUtil;

import jakarta.validation.Valid;

@Service
public class AddressBookService implements IAddressBookService {
	
	@Autowired
	AddressBookRespository repository;
	
	@Autowired
	AddressBookTokenUtil tokenUtil;

	//Post the user in an AddressBook
	@Override
	public AddressBookModel postData(AddressBookModelDTO model) {
		
		AddressBookModel user = new AddressBookModel(model);
		repository.save(user);

		String token = tokenUtil.createToken(user.getPersonId());
		System.out.println(token);
		return user;
	}

	//See all list the user in an AddressBook 
	@Override
	public List<AddressBookModel> getAllUser() {
		return repository.findAll();
	}

	//Get the user by id 
	@Override
	public AddressBookModel getUserById(int id) {
		Optional<AddressBookModel> user = repository.findById(id);
		if(user.isPresent()) {			
			return user.get();
		}
		else throw new AddressBookException ("User Not Found");
	}

	// Delete the user by id
	@Override
	public String deletUser(int id) {
		Optional<AddressBookModel> user = repository.findById(id);
		if(user.isPresent()) {
			repository.deleteById(id);
			return "User deleted Successfully,for id" +id;
		} 
		else throw new AddressBookException ("User Not Found");	
	}

	//Update the user by id
	@Override
	public AddressBookModel updateUser(int id, AddressBookModelDTO model) {
	
		Optional <AddressBookModel> user = repository.findById(id);
		if(user.isPresent()) {
		
		user.get().setName(model.getName());
		user.get().setCity(model.getCity());
		user.get().setState(model.getState());
		user.get().setZip(model.getZip());
		user.get().setEmail(model.getEmail());
		user.get().setPhoneNumber(model.getPhoneNumber());
		repository.save(user.get());
		return user.get();
		}
		else throw new AddressBookException ("User not found");
		
	}

	//Get details of the user by email 
	@Override
	public AddressBookModel getByEmail(String email) {
		
		Optional <AddressBookModel> user = repository.findByEmail(email);
		if (user.isPresent()) {
			return user.get();
		}
		else throw new AddressBookException ("Email not founded ");
	}

	@Override
	public AddressBookModel getUserByToken(String token) {
		
		int userId = tokenUtil.decodeToken(token);
		return repository.findById(userId).get();
	}

	@Override
	public AddressBookModel updateToken(String token, @Valid AddressBookModelDTO model) {
		
		int userId = tokenUtil.decodeToken(token);
	    Optional<AddressBookModel> user = repository.findById(userId);

	        user.get().setName(model.getName());
	        user.get().setCity(model.getCity());
	        user.get().setState(model.getState());
	        user.get().setZip(model.getZip());
	        user.get().setEmail(model.getEmail());
	        user.get().setPhoneNumber(model.getPhoneNumber());
	        repository.save(user.get());
	        return user.get();
	   
	}

}
