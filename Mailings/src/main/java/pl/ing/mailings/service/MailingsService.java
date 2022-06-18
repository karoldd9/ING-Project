package pl.ing.mailings.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.ing.mailings.entities.MailMessage;

@Service
public class MailingsService {

    private final JavaMailSender javaMailSender;

    public MailingsService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(MailMessage mailMessage) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("no-reply@localhost");
        mail.setText(mailMessage.getContent());
        mail.setTo(mailMessage.getEmail());

        javaMailSender.send(mail);
    }
}
