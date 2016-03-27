package org.ernest.applications.bt.gui.services;

public interface MailService {

	 void activate(String token, String name, String email);

	 void recover(String email, String pass);
}
