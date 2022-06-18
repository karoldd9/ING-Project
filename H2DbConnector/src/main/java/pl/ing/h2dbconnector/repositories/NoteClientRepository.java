package pl.ing.h2dbconnector.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ing.h2dbconnector.entities.NoteClient;

@Repository
public interface NoteClientRepository extends CrudRepository<NoteClient, Long> {
}
