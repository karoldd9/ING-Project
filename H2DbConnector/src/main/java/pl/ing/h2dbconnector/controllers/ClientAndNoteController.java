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
import pl.ing.h2dbconnector.entities.NoteClient;
import pl.ing.h2dbconnector.entities.dto.ClientDataDto;
import pl.ing.h2dbconnector.entities.dto.NoteClientDto;
import pl.ing.h2dbconnector.filters.ClientDataFilter;
import pl.ing.h2dbconnector.filters.NoteFilter;
import pl.ing.h2dbconnector.services.ClientDataService;
import pl.ing.h2dbconnector.services.NoteClientService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class ClientAndNoteController {

    @Autowired
    private ClientDataService clientDataService;

    @Autowired
    private NoteClientService noteClientService;

    @PostMapping("/{customerId}/saveNote")
    public NoteClientDto saveNote(@RequestBody @Valid NoteClientDto noteClientDto, @PathVariable("customerId") Long customerId) {

        noteClientDto.setType(noteClientDto.getType().toUpperCase());

        if(noteClientDto.getType().equals("ALERT")) {
            noteClientDto.setContent("Klient potrzebuje pomocy");
        } else if(!noteClientDto.getType().equals("INFO")) {
            noteClientDto.setType("INFO");
        }

        ClientData clientData = clientDataService.getClientDataByCustomerId(customerId).get(0);

        System.out.println("NoteClientDto: "+noteClientDto);
        NoteClient noteClient = NoteClient.of(noteClientDto, clientData);
        System.out.println("NoteClient: "+noteClient);
        return NoteClientDto.of(noteClientService.saveNoteClient(noteClient));
    }

    @GetMapping("/{customerId}/getAllNotes")
    public ResponseEntity<List<NoteClientDto>> getAllNotes(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(NoteClientDto.ofList(noteClientService.getNoteClientByCustomerId(customerId)));
    }

    @GetMapping("/{customerId}/note?since={since}&until={until}")
    public ResponseEntity<List<NoteClientDto>> getCustomerNotesSinceAndUntil(@PathVariable("customerId") Long customerId, @PathVariable("since") String since, @PathVariable("until") String until) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate sinceDate = LocalDate.parse(since, formatter);
        LocalDate untilDate = LocalDate.parse(until, formatter);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(NoteClientDto.ofList(NoteFilter.filterSinceAndUntil(noteClientService.getNoteClientByCustomerId(customerId), sinceDate, untilDate)));
    }

    @PostMapping
    public ClientDataDto saveClientData(@RequestBody @Valid ClientDataDto clientDataDto) {
        if(clientDataDto.getClientDataId() != null) {
            clientDataDto.setClientDataId(null);
        }
        return ClientDataDto.of(clientDataService.saveClientData(ClientData.of(clientDataDto)));
    }

    @GetMapping("/getAllCustomerData")
    public ResponseEntity<List<ClientDataDto>> getAllCLientData() {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ClientDataDto.ofList(clientDataService.getAllClientData()));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<ClientDataDto>> getClientDataByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ClientDataDto.ofList(clientDataService.getClientDataByCustomerId(customerId)));
    }

    @GetMapping("/{customerId}?date={date}")
    public ResponseEntity<List<ClientDataDto>> getHistoricalClientDataByDateAndCustomerId(@PathVariable("customerId") Long customerId, @PathVariable("date") String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<ClientData> clientDataList = clientDataService.getClientDataByCustomerId(customerId);
        List<ClientData> result = ClientDataFilter.filterByDate(clientDataList, localDate);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ClientDataDto.ofList(result));
    }

    @GetMapping("/{customerId}/last30days")
    public ResponseEntity<List<ClientDataDto>> getClientDataByLast30Days(@PathVariable("customerId") Long customerId) {

        List<ClientData> clientDataList = clientDataService.getClientDataByCustomerId(customerId);
        List<ClientData> result = ClientDataFilter.filterAfterDate(clientDataList, LocalDate.now().minusDays(30));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ClientDataDto.ofList(result));
    }
}
