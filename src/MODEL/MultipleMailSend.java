/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import static Logger.Log.warning;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author abhi
 */
public class MultipleMailSend {

    private static ResultSet rs;
    private static Vector<String> v;
    static public Thread sendMail;
    private static String subject;
    private static String message;

    public static void mails(ResultSet rs, String subject, String message) {
        MultipleMailSend.rs = rs;
        MultipleMailSend.subject = subject;
        MultipleMailSend.message = message;

        Thread sendMail = new Thread() {

            @Override
            public void run() {

                try {
                    while (rs.next()) {

                        sendMail(rs.getString("email"), subject, message);
                        System.out.println("in");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    warning(e);
                }
                System.out.println("mail sending finished");
                this.stop();
            }

        };

        MultipleMailSend.sendMail = sendMail;

    }

    public static void mails(Vector<String> v, String subject, String message) {
        MultipleMailSend.v = v;
        MultipleMailSend.subject = subject;
        MultipleMailSend.message = message;

        Thread sendMail = new Thread() {

            @Override
            public void run() {

                try {
                   
                    for(int x = 0; x < v.size(); x++){
                    
                        sendMail(v.get(x), subject, message);
                        System.out.println("in");
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    warning(e);
                }
                System.out.println("mail sending finished");
                this.stop();
            }

        };

        MultipleMailSend.sendMail = sendMail;

    }

    public static void sendMail(String to, String subject, String msg) {

        final String username = "email";
        final String password = "password";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        try {

            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info.cms.sl@gmail.com"));
            message.setRecipients(
                    javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(msg);

            javax.mail.Transport.send(message);

            System.out.println("Done");

        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
            warning(e);
        }

    }

}
