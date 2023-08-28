package com.addressbook.addressbookdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressBookModelDTO {
	
	@Pattern (regexp="^[A-Z]{1}[a-zA-Z]{2,}$", message = "Naming convention should as per condition")
	private String name;
	private String city;
	private String state;
	private int zip;
	@Pattern(regexp="[A-Za-z0-9+_.]+@(.+)$", message = "Format of the email as per validation")
	private String email;
	//@NotNull
	private long phoneNumber;

}
