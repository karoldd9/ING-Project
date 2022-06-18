package pl.ing.h2dbconnector.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ing.h2dbconnector.entities.NoteClient;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoteClientDto {    private Long customerId;
    private String type, title, content;

    public static NoteClientDto of(NoteClient noteClient) {
        NoteClientDto noteClientDto = new NoteClientDto(
                noteClient.getClientData().getCustomerId(),
                noteClient.getType(),
                noteClient.getTitle(),
                noteClient.getContent()
        );

        return noteClientDto;
    }

    public static List<NoteClientDto> ofList(List<NoteClient> noteClients) {
        return noteClients.stream().map(NoteClientDto::of).toList();
    }

    @Override
    public String toString() {
        return "NoteClientDto{" +
                ", clientDataId=" + customerId +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
