package com.addressbook.addressbookcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.addressbookdto.AddressBookModelDTO;
import com.addressbook.addressbookdto.responseDTO;
import com.addressbook.addressbookentity.AddressBookModel;
import com.addressbook.addressbookservice.IAddressBookService;

import jakarta.validation.Valid;

@RestController
public class AddressBookController {
	
	@Autowired
	IAddressBookService service;
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {
		return "Welcome to the AdderessBook";
	}
	
	//Post the user in an AddressBook
	@PostMapping("/post")
	public ResponseEntity<responseDTO> postUser(@Valid @RequestBody AddressBookModelDTO model)
	{
		AddressBookModel details = service.postData(model);
		responseDTO response = new responseDTO(details,"User Added Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.CREATED);
	}
	
	//See all list the user in an AddressBook 
	@GetMapping("/getAll")
	public ResponseEntity<responseDTO> allUser()
	{
		List<AddressBookModel> details = service.getAllUser();
		responseDTO response = new responseDTO (details,"User Showing successfully");
		return new ResponseEntity <responseDTO>(response,HttpStatus.OK);
	}
	
	//Get the user by id 
	@GetMapping("/getById/{id}")
	public ResponseEntity<responseDTO> getById(@PathVariable int id)
	{
		AddressBookModel User = service.getUserById(id);
		responseDTO response = new responseDTO(User,"employee get by id Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Delete the user by id
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<responseDTO> deleteById (@PathVariable int id)
	{
		String details = service.deletUser(id);
		responseDTO response = new responseDTO(details,"Delete user successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	//Update the user by id
	@PutMapping("/updateById/{id}")
	public ResponseEntity<responseDTO> updateUserById (@PathVariable int id,@Valid @RequestBody AddressBookModelDTO model)
	{
		AddressBookModel details = service.updateUser(id,model);
		responseDTO response = new responseDTO(details,"Update user successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.CREATED);
	}
	
	//Get details of the user by email 
	@GetMapping("/getByEmail/{email}")
	public ResponseEntity<responseDTO> postEmail(@PathVariable String email)
	{			
		AddressBookModel details = service.getByEmail(email);
		responseDTO response = new responseDTO(details,"Get Email by Information Successfully ");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	@GetMapping("/getByToken/{token}")
	public ResponseEntity<responseDTO> getByToken(@PathVariable String token)
	{
		AddressBookModel User = service.getUserByToken(token);
		responseDTO response = new responseDTO(User,"employee get by id Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	@PutMapping("/updateByToken/{token}")
	public ResponseEntity<responseDTO> updateUserByToken (@PathVariable String token,@Valid @RequestBody AddressBookModelDTO model)
	{
		AddressBookModel details = service.updateToken(token,model);
		responseDTO response = new responseDTO(details,"Update user successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.CREATED);
	}
}
