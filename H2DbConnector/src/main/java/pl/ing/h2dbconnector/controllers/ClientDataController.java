package pl.ing.h2dbconnector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.h2dbconnector.entities.ClientData;
import pl.ing.h2dbconnector.services.ClientDataService;

@RestController
public class ClientDataController {

    @Autowired
    private ClientDataService clientDataService;

    @PostMapping
    public ClientData saveClientData(ClientData clientData) {
        return clientDataService.saveClientData(clientData);
    }
}
