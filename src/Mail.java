import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    final static String IMG = "<img src=\"https://www.mapcasablanca.ma/map/uploads/2021/06/CNSS-3.jpg\" alt=\"logo\" width=\"100\" height=\"100\">";
    public static Boolean sendMail(String code, String email) {
        final String username = "ibnahmadmohamed8@gmail.com"; //
        final String password = "rtmouzprbcfllqgh";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("elhaoudihaytham2001@gmail.com"));
            message.setSubject("Verification code MaCnss");
            message.setText("Your code is: "+code + "\n This code is valid for 5 minutes \n ");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }


}

