package org.ernest.applications.bt.gui.services;

public interface AuthDataService {

	String validate(String name, String password);

	void createUser(String name, String email, String pass);

	void activate(String token);

	void recover(String email);

}
