package br.ufes.dwws.suggestionSpace.login;

public class LoginFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	private LoginFailedReason reason;

	public LoginFailedException(LoginFailedReason reason) {
		this.reason = reason;
	}

	public LoginFailedException(Throwable t, LoginFailedReason reason) {
		super(t);
		this.reason = reason;
	}


	public enum LoginFailedReason {

		UNKNOWN_USERNAME("unknownUsername"), 
		INCORRECT_PASSWORD("incorrectPassword"), 
		MULTIPLE_USERS(	"multipleUsers"),
		MD5_ERROR("md5Error"), 
		CONTAINER_REJECTED("containerRejected"), 
		NO_HTTP_REQUEST("noHttpRequest");

		private String id;

		private LoginFailedReason(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return id;
		}
	}
	

	public LoginFailedReason getReason() {
		return reason;
	}
}
