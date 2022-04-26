package uz.iSystem.market.bookmarket.auth.mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Value("${mail.subject}")
    private String mailSubject;
    private final MailSender mailSender;


    public MailSenderService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String content, String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(content);
        simpleMailMessage.setSubject(mailSubject);
        mailSender.send(simpleMailMessage);
    }
}
