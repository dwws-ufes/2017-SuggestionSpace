package br.ufes.dwws.suggestionSpace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class User extends PersistentObjectSupport {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String phone;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	private String email;
	private String password;
	private String role;
	
	

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", phone=" + phone + ", birthday=" + birthday + ", email="
				+ email + ", password=" + password + ", role=" + role + "]";
	}
	
	

	
	
}
