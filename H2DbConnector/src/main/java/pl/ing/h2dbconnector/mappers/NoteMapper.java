package pl.ing.h2dbconnector.mappers;

import pl.ing.h2dbconnector.entities.NoteClient;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NoteMapper {

    public static List<NoteClient> NoteClientListOfIterator(Iterator<NoteClient> noteClientIterator) {
        List<NoteClient> result = new LinkedList<>();

        while (noteClientIterator.hasNext()) {
            result.add(noteClientIterator.next());
        }

        return result;
    }
}
