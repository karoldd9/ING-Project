package pl.ing.h2dbconnector.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ing.h2dbconnector.entities.dto.ClientDataDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class ClientData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientDataId;

    private LocalDate infoAsOfDate;

    private Long customerId;

    private String customerName;

    private LocalDate customerStartDate;

    private String customerType;

    private BigDecimal customerIncome;

    private String customerRiskClass;

    private String customerBusinessType;

    private Double R1;

    private Double R2;

    public static ClientData of(ClientDataDto clientDataDto) {
        ClientData clientData = new ClientData(
                clientDataDto.getClientDataId(),
                clientDataDto.getInfoAsOfDate(),
                clientDataDto.getCustomerId(),
                clientDataDto.getCustomerName(),
                clientDataDto.getCustomerStartDate(),
                clientDataDto.getCustomerType(),
                clientDataDto.getCustomerIncome(),
                clientDataDto.getCustomerRiskClass(),
                clientDataDto.getCustomerBusinessType(),
                clientDataDto.getR1(),
                clientDataDto.getR2()
        );

        return clientData;
    }

    public static List<ClientData> ofList(List<ClientDataDto> clientDataDtos) {
        return clientDataDtos.stream().map(ClientData::of).toList();
    }
}
