package pl.ing.h2dbconnector.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ing.h2dbconnector.entities.dto.NoteClientDto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Builder
public class NoteClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private ClientData clientData;

    private String type, title, content;

    private LocalDate created;

    public static NoteClient of(NoteClientDto noteClientDto, ClientData clientData) {
        NoteClient noteClient = new NoteClient(
                null,
                clientData,
                noteClientDto.getType(),
                noteClientDto.getTitle(),
                noteClientDto.getContent(),
                LocalDate.now()
        );

        return noteClient;
    }

    @Override
    public String toString() {
        return "NoteClient{" +
                "id=" + id +
                ", clientData=" + clientData +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
