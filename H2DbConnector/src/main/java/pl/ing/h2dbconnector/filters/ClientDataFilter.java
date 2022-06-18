package pl.ing.h2dbconnector.filters;

import pl.ing.h2dbconnector.entities.ClientData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDataFilter {
    public static List<ClientData> filterByDate(List<ClientData> clientDataList, LocalDate date) {
        List<ClientData> result = new LinkedList<>();

        for(ClientData clientData: clientDataList) {
            if(clientData.getInfoAsOfDate().getDayOfMonth() == date.getDayOfMonth()
                    && clientData.getInfoAsOfDate().getMonthValue() == date.getMonthValue()
                    && clientData.getInfoAsOfDate().getYear() == date.getYear()) {
                result.add(clientData);
            }
        }

        return result;
    }

    public static List<ClientData> filterAfterDate(List<ClientData> clientDataList, LocalDate date) {
        List<ClientData> result = new LinkedList<>();

        for(ClientData clientData: clientDataList) {
            if(clientData.getInfoAsOfDate().isAfter(date)) {
                result.add(clientData);
            }
        }

        return result;
    }
}
