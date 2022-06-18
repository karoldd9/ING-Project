package pl.ing.h2dbconnector.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ing.h2dbconnector.entities.ClientData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDataDto {
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

    public static ClientDataDto of(ClientData clientData) {
        ClientDataDto clientDataDto = new ClientDataDto(
                clientData.getClientDataId(),
                clientData.getInfoAsOfDate(),
                clientData.getCustomerId(),
                clientData.getCustomerName(),
                clientData.getCustomerStartDate(),
                clientData.getCustomerType(),
                clientData.getCustomerIncome(),
                clientData.getCustomerRiskClass(),
                clientData.getCustomerBusinessType(),
                clientData.getR1(),
                clientData.getR2()
        );

        return clientDataDto;
    }

    public static List<ClientDataDto> ofList(List<ClientData> clientDataList) {
        return clientDataList.stream().map(ClientDataDto::of).toList();
    }
}
