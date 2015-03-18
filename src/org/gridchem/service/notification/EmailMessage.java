/*Copyright (c) 2004,University of Illinois at Urbana-Champaign.  All rights reserved.
 * 
 * Created on May 10, 2007
 * 
 * Developed by: CCT, Center for Computation and Technology, 
 * 				NCSA, University of Illinois at Urbana-Champaign
 * 				OSC, Ohio Supercomputing Center
 * 				TACC, Texas Advanced Computing Center
 * 				UKy, University of Kentucky
 * 
 * https://www.gridchem.org/
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal with the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom 
 * the Software is furnished to do so, subject to the following conditions:
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimers.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimers in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the names of Chemistry and Computational Biology Group , NCSA, 
 *    University of Illinois at Urbana-Champaign, nor the names of its contributors 
 *    may be used to endorse or promote products derived from this Software without 
 *    specific prior written permission.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  
 * IN NO EVENT SHALL THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS WITH THE SOFTWARE.
*/

package org.gridchem.service.notification;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.gridchem.service.beans.UserBean;
import org.gridchem.service.exceptions.NotificationException;
import org.gridchem.service.exceptions.PermissionException;
import org.gridchem.service.model.Notification;
import org.gridchem.service.persistence.HibernateUtil;
import org.gridchem.service.util.Settings;

import com.sun.mail.smtp.SMTPSSLTransport;

/**
 * Simple email class using the javamail API to send an email to the 
 * user using their contact information from the DB.  Good times to send
 * notification would be when their account is activated, job start, stop,
 * and migrations.
 * 
 * @author Rion Dooley < dooley [at] tacc [dot] utexas [dot] edu >
 *
 */
public class EmailMessage {
    
    public static Logger log = Logger.getLogger(EmailMessage.class.getName());
    
    public static void send(UserBean user, String subject, String text) throws NotificationException {
        Session session = null;
        
        Properties props = new Properties();
        
        try {
            props.put("mail.smtps.host", Settings.MAIL_SERVER);
            props.put("mail.smtps.auth",Settings.MAILSMTPSPROTOCOL);
            
            session = Session.getInstance(props);
            
            MimeMessage message = createMessageObject(session, 
                    subject, text, user);
            
            SMTPSSLTransport transport = (SMTPSSLTransport)session.getTransport("smtps");
            
            transport.connect(Settings.MAIL_SERVER,Settings.MAILLOGIN, Settings.MAILPASSWORD);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            
        } catch (Exception e) {
            
            log.error(e);
            throw new NotificationException("Email notification failed.",e);
            
        }
    }
    
    public static void send(UserBean user, Notification n) throws NotificationException {
        
        Session session = null;
        
        Properties props = new Properties();
        
        try {
            
            Authenticator auth = new MailAuthenticator();
            props.put("mail.smtps.host",Settings.MAIL_SERVER);
            props.put("mail.smtps.auth",Settings.MAILSMTPSPROTOCOL);
            
            session = Session.getInstance(props,auth);
            
            MimeMessage message = createMessageObject(session, 
                    n.getSubject(), n.getMessage(), user);
            
            Transport transport = session.getTransport("smtps");
            
            transport.connect(Settings.MAIL_SERVER,Settings.MAILLOGIN, Settings.MAILPASSWORD);
            
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            
            // Mark the notification as sent.
            org.hibernate.Session s = HibernateUtil.getSession();
            s.beginTransaction();
            n.setDelivered(true);
            s.saveOrUpdate(n);
            s.getTransaction().commit();
            
        } catch (Exception e) {
            
            log.error(e);
            throw new NotificationException("Email notification failed.",e);
            
        }
        
    }
    
    private static MimeMessage createMessageObject(Session session, 
                                                    String subject, 
                                                    String text, 
                                                    UserBean user) 
    throws Exception {
        
        MimeMessage message = new MimeMessage(session);
        
        message.setText(text);
        
        message.setSubject(subject);
        
        Address fromAddress = new InternetAddress(Settings.MAILLOGIN, "GridChem Middleware Service");
        
        Address toAddress = new InternetAddress(user.getEmail(), 
                user.getFirstName() + " " + user.getLastName());
        
        message.setFrom(fromAddress);
        
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        return message;
    }    
}


class MailAuthenticator extends Authenticator {
    private String username = null;
    private String password = null;
    
    public MailAuthenticator() throws Exception{
        
        this.username = Settings.MAILLOGIN;
        
        this.password = Settings.MAILPASSWORD;
    }
    
    public PasswordAuthentication getPasswordAuthentication() throws PermissionException {
        
        if (username != null && password != null) {
            
            return new PasswordAuthentication(username,password);
        
        } else {
        
            throw new PermissionException("No username and password found to authenticate with SMTP mail server.");
        
        }
    }
}