package pl.ing.mailings.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MailingsControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String payload =
            "{" +
                    "\n\t\"addressee\": \"client@mail.com\"," +
                    "\n\t\"subject\": \"Some subject\"," +
                    "\n\t\"body\": \"Some body\"}";

    @Test
    void greenMailtest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(payload, headers);
        ResponseEntity<Void> response = this.testRestTemplate.postForEntity("/mail", request, Void.class);

        assertEquals(200, response.getStatusCodeValue());
    }

}