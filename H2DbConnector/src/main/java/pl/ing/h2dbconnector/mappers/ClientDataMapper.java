package pl.ing.h2dbconnector.mappers;

import pl.ing.h2dbconnector.entities.ClientData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ClientDataMapper {
    public static List<ClientData> mapIteratorToList(Iterator<ClientData> iterator) {
        List<ClientData> clientDataList = new LinkedList<>();

        while(iterator.hasNext()) {
            clientDataList.add(iterator.next());
        }

        return clientDataList;
    }
}
