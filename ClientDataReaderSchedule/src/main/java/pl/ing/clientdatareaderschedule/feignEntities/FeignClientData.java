package pl.ing.clientdatareaderschedule.feignEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeignClientData implements Serializable {
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

    @Override
    public String toString() {
        return "FeignClientData{" +
                "clientDataId=" + clientDataId +
                ", infoAsOfDate=" + infoAsOfDate +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerStartDate=" + customerStartDate +
                ", customerType='" + customerType + '\'' +
                ", customerIncome=" + customerIncome +
                ", customerRiskClass='" + customerRiskClass + '\'' +
                ", customerBusinessType='" + customerBusinessType + '\'' +
                ", R1=" + R1 +
                ", R2=" + R2 +
                '}';
    }
}
