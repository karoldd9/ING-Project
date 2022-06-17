package pl.ing.h2dbconnector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.h2dbconnector.entities.ClientData;
import pl.ing.h2dbconnector.filters.ClientDataFilter;
import pl.ing.h2dbconnector.services.ClientDataService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class ClientDataController {

    @Autowired
    private ClientDataService clientDataService;

    @PostMapping
    public ClientData saveClientData(@RequestBody @Valid ClientData clientData) {
        return clientDataService.saveClientData(clientData);
    }

    @GetMapping("/getAllCustomerData")
    public ResponseEntity<List<ClientData>> getAllCLientData() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientDataService.getAllClientData());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ClientData>> getClientDataByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientDataService.getClientDataByCustomerId(customerId));
    }

    @GetMapping("/{customerId}?date={date}")
    public ResponseEntity<List<ClientData>> getHistoricalClientDataByDateAndCustomerId(@PathVariable("customerId") Long customerId, @PathVariable("date") String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<ClientData> clientDataList = clientDataService.getClientDataByCustomerId(customerId);
        List<ClientData> result = ClientDataFilter.filterByDate(clientDataList, localDate);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @GetMapping("{customerId}/last30days")
    public ResponseEntity<List<ClientData>> getClientDataByLast30Days(@PathVariable("customerId") Long customerId) {

        List<ClientData> clientDataList = clientDataService.getClientDataByCustomerId(customerId);
        List<ClientData> result = ClientDataFilter.filterAfterDate(clientDataList, LocalDate.now().minusDays(30));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
}
