package pl.ing.h2dbconnector.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import pl.ing.h2dbconnector.entities.ClientData;
import pl.ing.h2dbconnector.entities.NoteClient;
import pl.ing.h2dbconnector.entities.dto.NoteClientDto;
import pl.ing.h2dbconnector.services.ClientDataService;
import pl.ing.h2dbconnector.services.NoteClientService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
class ClientAndNoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    ClientDataService clientDataService;

    @Mock
    NoteClientService noteClientService;

    @InjectMocks
    ClientAndNoteController clientAndNoteController;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting tests...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finished tests");
    }

    @Test
    void getAllCustomerDataTest() throws Exception {
        List<ClientData> clientDataList = new LinkedList<>();

        ClientData client1 = new ClientData();
        client1.setCustomerName("Test1");
        clientDataList.add(client1);

        ClientData client2 = new ClientData();
        client2.setCustomerName("Test2");
        clientDataList.add(client2);

        ClientData client3 = new ClientData();
        client3.setCustomerName("Test3");
        clientDataList.add(client3);


        Mockito.when(clientDataService.getAllClientData()).thenReturn(clientDataList);

        List<ClientData> result = ClientData.ofList(clientAndNoteController.getAllCLientData().getBody());

        for(ClientData clientData: result) {
            System.out.println(clientData);
        }

        assertEquals(3, result.size());
    }

    @Test
    void findClientDataByIdTest() {
        List<ClientData> clientDataList = new LinkedList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse("2020-01-01", formatter);
        LocalDate localDate2 = LocalDate.parse("2021-01-01", formatter);
        LocalDate localDate3 = LocalDate.parse("2022-01-01", formatter);

        ClientData clientData1 = new ClientData();
        clientData1.setInfoAsOfDate(localDate1);
        clientDataList.add(clientData1);

        ClientData clientData2 = new ClientData();
        clientData2.setInfoAsOfDate(localDate2);
        clientDataList.add(clientData2);

        ClientData clientData3 = new ClientData();
        clientData3.setInfoAsOfDate(localDate3);
        clientDataList.add(clientData3);

        Mockito.when(clientDataService.getClientDataByCustomerId(1L)).thenReturn(clientDataList);

        List<ClientData> result = ClientData.ofList(clientAndNoteController.getHistoricalClientDataByDateAndCustomerId(1L, "2020-01-01").getBody());

        assertEquals(1, result.size());
    }

    @Test
    void testLast30days() {
        List<ClientData> clientDataList = new LinkedList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse("2020-01-01", formatter);
        LocalDate localDate2 = LocalDate.parse("2021-01-01", formatter);
        LocalDate localDate3 = LocalDate.parse("2022-06-01", formatter);

        ClientData clientData1 = new ClientData();
        clientData1.setInfoAsOfDate(localDate1);
        clientDataList.add(clientData1);

        ClientData clientData2 = new ClientData();
        clientData2.setInfoAsOfDate(localDate2);
        clientDataList.add(clientData2);

        ClientData clientData3 = new ClientData();
        clientData3.setInfoAsOfDate(localDate3);
        clientDataList.add(clientData3);

        Mockito.when(clientDataService.getClientDataByCustomerId(1L)).thenReturn(clientDataList);

        List<ClientData> result = ClientData.ofList(clientAndNoteController.getClientDataByLast30Days(1L).getBody());

        assertEquals(1, result.size());
    }

    @Test
    void testGetNotesSinceAndUntil() {
        List<NoteClient> noteClientList = new LinkedList<>();

        ClientData clientData = new ClientData();

        clientData.setCustomerId(1L);
        List<ClientData> clientDataList = new ArrayList<>();
        clientDataList.add(clientData);

        LocalDate date1 = LocalDate.now().minusDays(60);
        NoteClient noteClient1 = new NoteClient();
        noteClient1.setCreated(date1);
        noteClient1.setClientData(clientData);

        LocalDate date2 = LocalDate.now().minusDays(30);
        NoteClient noteClient2 = new NoteClient();
        noteClient2.setCreated(date2);
        noteClient2.setClientData(clientData);

        LocalDate date3 = LocalDate.now().minusDays(10);
        NoteClient noteClient3 = new NoteClient();
        noteClient3.setCreated(date3);
        noteClient3.setClientData(clientData);

        LocalDate date4 = LocalDate.now();
        NoteClient noteClient4 = new NoteClient();
        noteClient4.setCreated(date4);
        noteClient4.setClientData(clientData);

        noteClientList.add(noteClient1);
        noteClientList.add(noteClient2);
        noteClientList.add(noteClient3);
        noteClientList.add(noteClient4);

        Mockito.when(noteClientService.getNoteClientByCustomerId(1L)).thenReturn(noteClientList);

        List<NoteClientDto> result = clientAndNoteController.getCustomerNotesSinceAndUntil(1L, "2022-06-01", "2022-06-30").getBody();

        assertEquals(2, result.size());
    }
}