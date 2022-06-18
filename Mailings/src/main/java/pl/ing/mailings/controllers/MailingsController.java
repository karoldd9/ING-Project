package pl.ing.mailings.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.mailings.entities.MailMessage;

import javax.validation.Valid;

@RestController
@RequestMapping("/mail")
public class MailingsController {

    @PostMapping
    public void sendMail(@RequestBody @Valid MailMessage mailMessage) {
        System.out.println(mailMessage);
    }
}
