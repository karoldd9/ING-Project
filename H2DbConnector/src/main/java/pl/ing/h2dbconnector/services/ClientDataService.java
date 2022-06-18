package pl.ing.h2dbconnector.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ing.h2dbconnector.entities.ClientData;
import pl.ing.h2dbconnector.mappers.ClientDataMapper;
import pl.ing.h2dbconnector.repositories.ClientDataRepository;

import java.util.List;

@Service
public class ClientDataService {

    @Autowired
    private ClientDataRepository clientDataRepository;

    public ClientData saveClientData(ClientData clientData) {
        return clientDataRepository.save(clientData);
    }

    public List<ClientData> getAllClientData() {
        return ClientDataMapper.mapIteratorToList(clientDataRepository.findAll().iterator());
    }

    public List<ClientData> getClientDataByCustomerId(Long customerId) {

        return clientDataRepository.findByCustomerId(customerId);
    }
}
