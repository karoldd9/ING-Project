package pl.ing.h2dbconnector.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ing.h2dbconnector.entities.ClientData;
import pl.ing.h2dbconnector.repositories.ClientDataRepository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ClientDataService {

    @Autowired
    private ClientDataRepository clientDataRepository;

    public ClientData saveClientData(ClientData clientData) {
        return clientDataRepository.save(clientData);
    }

    public List<ClientData> getAllClientData() {
        Iterator<ClientData> clientDataIterator = clientDataRepository.findAll().iterator();

        List<ClientData> clientData = new LinkedList<>();

        while(clientDataIterator.hasNext()) {
            clientData.add(clientDataIterator.next());
        }

        return clientData;
    }
}
