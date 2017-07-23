package br.ufes.inf.nemo.marvin.core.controller;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.application.UserFavoriteService;

@Named
@ConversationScoped
public class UserFavoriteController extends JSFController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserFavoriteService userFavoriteService;
	@Inject
	private Conversation conversation;
	
	public UserFavoriteService getUserFavoriteService() {
		return userFavoriteService;
	}
	public void setUserFavoriteService(UserFavoriteService userFavoriteService) {
		this.userFavoriteService = userFavoriteService;
	}
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	
}
