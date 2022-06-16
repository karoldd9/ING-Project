package pl.ing.h2dbconnector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.h2dbconnector.entities.ClientData;
import pl.ing.h2dbconnector.services.ClientDataService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
public class ClientDataController {

    @Autowired
    private ClientDataService clientDataService;

    @PostMapping
    public ClientData saveClientData(@RequestBody @Valid ClientData clientData) {
        return clientDataService.saveClientData(clientData);
    }

    @PostMapping("/siemanko")
    public void test() {
        System.out.println("Siemanko :)");
    }

    @GetMapping
    public ResponseEntity<List<ClientData>> getAllCLientData() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientDataService.getAllClientData());
    }
}
