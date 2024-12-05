package com.plateplan.email;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {

    public static void sendEmail(String toEmail, String subject, String body) {
 
      System.out.println("sendEmail method called");
    	
    	
        final String fromEmail = "plateplan0@gmail.com"; 
        final String password = "fstl cgzi vmvl vkrz";

     // Set email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); 
        properties.put("mail.smtp.port", "587"); 
        properties.put("mail.smtp.auth", "true"); 
        properties.put("mail.smtp.starttls.enable", "true");

        
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        
        try {
       
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);

            
            String verificationLink = "http://localhost:8080/PlatePlan/verify?email=" + toEmail;
            String bodyWithLink;
            
            if (subject.equals("Your password is"))
            {
            bodyWithLink = body;
            }
            else 
            {
            bodyWithLink = body + "<br>Click to verify your email: <a href=\"" + verificationLink + "\">Verify Now</a>";
            }
            
            
            message.setContent(bodyWithLink, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        
    }


}
