package com.hangdude.model;

/**
 * A POJO class that represents a User
 * 
 * @author ahamouda
 */
public class Dude {
	private String firstName;
	private String lastName;
	private String email;
	private String phone;

	private Dude(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.phone = builder.phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String firstName;
		private String lastName;
		private String email;
		private String phone;

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.firstName = lastName;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Dude build() {
			return new Dude(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Dude other = (Dude) obj;
		if (email == null) {
			if (other.email != null) return false;
		} else if (!email.equals(other.email)) return false;
		if (firstName == null) {
			if (other.firstName != null) return false;
		} else if (!firstName.equals(other.firstName)) return false;
		if (lastName == null) {
			if (other.lastName != null) return false;
		} else if (!lastName.equals(other.lastName)) return false;
		if (phone == null) {
			if (other.phone != null) return false;
		} else if (!phone.equals(other.phone)) return false;
		return true;
	}

}
