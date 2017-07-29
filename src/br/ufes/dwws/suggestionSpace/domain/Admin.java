package br.ufes.dwws.suggestionSpace.domain;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufes.dwws.util.Role;

@Entity
public class Admin extends User {
	
	private static final long serialVersionUID = 1L;
		
	public Admin(){
		this.setRole(Role.ADMIN);
	}
}
