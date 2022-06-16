package pl.ing.h2dbconnector.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

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
}
