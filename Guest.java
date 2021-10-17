package p_01;

import java.io.Serializable;

public class Guest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	public static int compCase;
 
	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public Guest() {
		
	}
 
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
	public String getLastName() {
		return this.lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
 
	@Override
	public boolean equals(Object guest) {
		if (guest == null) {
			return false;
		}
		if (this == guest) {
			return true;
		}
		if (this.getClass() != guest.getClass()) {
			return false;
		}
		Guest auxGuest = (Guest) guest;
		
		if (compCase == 0) {
			if (this.lastName.equalsIgnoreCase(auxGuest.lastName)) {
				return true;
			}
			if (this.firstName.equalsIgnoreCase(auxGuest.firstName)) {
				return true;
			}
		}
		
		if (compCase == 1) {
			return compareName(auxGuest.lastName, auxGuest.firstName);
		}
		
		if (compCase == 2 || compCase == 0) {
			if (email == null) {
				if (auxGuest.email != null) {
					return false;
				}
			} else if (email.equalsIgnoreCase(auxGuest.email)) {
				return true;
			}
		}
		
		if (compCase == 3 || compCase == 0) {
			if (phoneNumber == null) {
				if (auxGuest.phoneNumber != null) {
					return false;
				}
			} else if (phoneNumber.equalsIgnoreCase(auxGuest.phoneNumber)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean compareName(String auxLastName, String auxFirstName) {
		if ((lastName == null && auxLastName != null) ||
				(firstName == null && auxFirstName != null)){
				return false;
		}
		if (lastName.equalsIgnoreCase(auxLastName) && 
				firstName.equalsIgnoreCase(auxFirstName)) {
			return true;
		}
		return false;
	}
	
	public String contains(String keyword) {
		keyword = keyword.toLowerCase();
		if (lastName.toLowerCase().contains(keyword)) {
			return "lastName " + lastName;
		}
		if (firstName.toLowerCase().contains(keyword)) {
			return "firstName " + firstName;
		}
		if (email.toLowerCase().contains(keyword)) {
			return "email " + email;
		}
		if (phoneNumber.toLowerCase().contains(keyword)) {
			return "phoneNumber " + phoneNumber;
		}
		return null;
	}
	
	@Override
	public String toString() {
		String output = String.format("Nume: %s %s, Email: %s, Telefon: %s",
				this.lastName, this.firstName, this.email, this.phoneNumber);
		return output;
	}
}
