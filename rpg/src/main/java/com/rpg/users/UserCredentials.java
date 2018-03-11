package com.rpg.users;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dinesh Thangaraj
 *
 *         Created on 11-Mar-2018
 */
public class UserCredentials implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String userEmail;
	private String creditCardNumber;
	private Date creditCardExpDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Date getCreditCardExpDate() {
		return creditCardExpDate;
	}

	public void setCreditCardExpDate(Date creditCardExpDate) {
		this.creditCardExpDate = creditCardExpDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCardExpDate == null) ? 0 : creditCardExpDate.hashCode());
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCredentials other = (UserCredentials) obj;
		if (creditCardExpDate == null) {
			if (other.creditCardExpDate != null)
				return false;
		} else if (!creditCardExpDate.equals(other.creditCardExpDate))
			return false;
		if (creditCardNumber == null) {
			if (other.creditCardNumber != null)
				return false;
		} else if (!creditCardNumber.equals(other.creditCardNumber))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserCredentials [userName=");
		builder.append(userName);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", creditCardNumber=");
		builder.append(creditCardNumber);
		builder.append(", creditCardExpDate=");
		builder.append(creditCardExpDate);
		builder.append("]");
		return builder.toString();
	}

}
