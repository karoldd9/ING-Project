package pl.ing.h2dbconnector.filters;

import pl.ing.h2dbconnector.entities.NoteClient;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class NoteFilter {

    public static List<NoteClient> filterByCustomerId(List<NoteClient> noteClientList, Long customerId) {
        return noteClientList.stream().filter(c -> c.getClientData().getCustomerId().equals(customerId)).toList();
    }

    public static List<NoteClient> filterSinceAndUntil(List<NoteClient> noteClientList, LocalDate since, LocalDate until) {
        List<NoteClient> result = new LinkedList<>();

        for(NoteClient noteClient: noteClientList) {
            if(noteClient.getCreated().isAfter(since.minusDays(1)) && noteClient.getCreated().isBefore(until.minusDays(1))) {
                result.add(noteClient);
            }
        }

        return result;
    }
}
