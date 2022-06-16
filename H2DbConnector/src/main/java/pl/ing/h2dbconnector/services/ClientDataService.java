package pl.ing.h2dbconnector.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ing.h2dbconnector.entities.ClientData;
import pl.ing.h2dbconnector.repositories.ClientDataRepository;

@Service
public class ClientDataService {

    @Autowired
    private ClientDataRepository clientDataRepository;

    public ClientData saveClientData(ClientData clientData) {
        return clientDataRepository.save(clientData);
    }
}
