/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pi.services;

import edu.esprit.pi.iservices.IEmailService;
import edu.esprit.pi.iservices.IUserService;
import edu.esprit.pi.models.Alerte;
import edu.esprit.pi.models.Annonce;
import edu.esprit.pi.models.Mail;
import edu.esprit.pi.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nacef Fethi
 */
public class MailService implements IEmailService {

    @Override
    public void envoyerMail(User u, List<Annonce> an, Alerte a) {
        UserService userservice= new UserService();
        User user = new User(1);
       // String to = user.getEmail();
        String to =userservice.findEmailByIdUser(user.getId());
        final String username = "decart.rannou@gmail.com";
        final String password = "118712rania";
        String contenu = " Cher notre Client de l'alerte  "+ a.getLieuDepart() + " ," + a.getLieuArrivee()+" ," + a.getDate()+ "   voici les publications relatifs à votre alerte \n \n ";
        Mail mail = new Mail(contenu, an);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

          System.out.println(to);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("decart.rania@gmail.com"));
            //System.out.println(to);
            InternetAddress recipient = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, recipient);
            //InternetAddress.parse("erp.evertek@gmail.com"));
            message.setSubject("Concernant votre alerte de covoiturage");
            message.setText(mail.toString());
            Transport.send(message);

            System.out.println("Done");

        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}
