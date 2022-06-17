package pl.ing.h2dbconnector.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.ing.h2dbconnector.entities.ClientData;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ClientDataRepository extends CrudRepository<ClientData, Long> {

    List<ClientData> findByCustomerId(Long customerId);
}
