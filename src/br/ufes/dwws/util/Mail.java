package br.ufes.dwws.util;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Mail {
 
    private Session session;
 
    public void send(String address, String topic, String textMessage) {
 
        try {
        	InitialContext ic = new InitialContext();       
        	session = (Session)ic.lookup("java:/jboss/mail/SuggestionSpace");
 
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
            message.setSubject(topic);
            message.setContent(textMessage, "text/html; charset=utf-8");
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        } catch (NamingException e) {
			e.printStackTrace();
		}
    }
}