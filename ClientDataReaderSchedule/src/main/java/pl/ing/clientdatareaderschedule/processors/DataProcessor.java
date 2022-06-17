package pl.ing.clientdatareaderschedule.processors;

import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import pl.ing.clientdatareaderschedule.httpConnector.HttpConnector;
import pl.ing.clientdatareaderschedule.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataProcessor {


    public boolean process(List<String> clientDataLines) {

        clientDataLines.remove(0); //remove column names from list

        for(String clientDataLine: clientDataLines) {
            FeignClientData feignClientData = setFeignClientDataFromStringList(
                    Validator.splitClientDataByParameter(clientDataLine, ",")
            );



            try {
                HttpConnector.postJson(Validator.FeignClientDataToJSONString(feignClientData));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return true;
    }

    private Double R1Process () {
        return 0D;
    }

    private Double R2Process () {
        return 0D;
    }

    private Double F1Process (String businessType) {
        if(businessType.toUpperCase().equals("BR_3")) {
            return 0.1D;
        } else {
            return 0.2D;
        }
    }

    private FeignClientData setFeignClientDataFromStringList(List<String> feignClientDataStringList) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new FeignClientData(
                null,
                LocalDate.parse(feignClientDataStringList.get(0), formatter),
                Long.valueOf(feignClientDataStringList.get(1)),
                feignClientDataStringList.get(2),
                LocalDate.parse(feignClientDataStringList.get(3), formatter),
                feignClientDataStringList.get(4),
                new BigDecimal(feignClientDataStringList.get(5)),
                feignClientDataStringList.get(6),
                feignClientDataStringList.get(7),
                0D,
                0D
        );
    }
}
