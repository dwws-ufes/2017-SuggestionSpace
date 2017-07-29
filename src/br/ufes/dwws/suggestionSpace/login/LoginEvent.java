package br.ufes.dwws.suggestionSpace.login;

import br.ufes.dwws.suggestionSpace.domain.User;

public class LoginEvent {
	
	private User user;


	public LoginEvent(User user) {
		super();
		this.user = user;
	}
	

	public User getUser() {
		return user;
	}
}
