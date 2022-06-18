package pl.ing.h2dbconnector.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ing.h2dbconnector.entities.NoteClient;
import pl.ing.h2dbconnector.entities.dto.NoteClientDto;
import pl.ing.h2dbconnector.filters.NoteFilter;
import pl.ing.h2dbconnector.repositories.NoteClientRepository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class NoteClientService {

    @Autowired
    NoteClientRepository noteClientRepository;

    public NoteClient saveNoteClient(NoteClient noteClient) {
        return noteClientRepository.save(noteClient);
    }

    public List<NoteClient> getAllNoteClient() {

        Iterator<NoteClient> noteClientIterator = noteClientRepository.findAll().iterator();

        List<NoteClient> noteClientList = new LinkedList<>();

        while (noteClientIterator.hasNext()) {
            noteClientList.add(noteClientIterator.next());
        }

        return noteClientList;
    }

    public List<NoteClient> getNoteClientByCustomerId(Long customerId) {
        return NoteFilter.filterByCustomerId(getAllNoteClient(), customerId);
    }
}
