package pl.ing.mailings.controllers;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.mailings.entities.MailMessage;
import pl.ing.mailings.service.MailingsService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/mailing")
public class MailingsController {

    private final MailingsService mailingsService;

    public MailingsController(MailingsService mailingsService) {
        this.mailingsService = mailingsService;
    }

    @PostMapping("/send")
    public void sendMail(@RequestBody @Valid MailMessage mailMessage) {
        mailingsService.send(mailMessage);
    }
}
