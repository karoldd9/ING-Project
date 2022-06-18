package pl.ing.clientdatareaderschedule.processors;

import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import pl.ing.clientdatareaderschedule.httpConnector.HttpConnector;
import pl.ing.clientdatareaderschedule.validation.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                if(feignClientData.getCustomerType().equalsIgnoreCase("TYPE_A1")) {

                    feignClientData.setR1(r1ProcessForTypeA1(feignClientData.getCustomerType(), feignClientData.getCustomerId(), feignClientData.getCustomerIncome()));
                    feignClientData.setR2(r2ProcessForTypeA1(feignClientData.getCustomerIncome(), feignClientData.getCustomerBusinessType()));

                } else if(feignClientData.getCustomerType().equalsIgnoreCase("TYPE_A5")
                        && feignClientData.getCustomerRiskClass().equalsIgnoreCase("A3")) {

                    feignClientData.setR1(r1ProcessForTypeA5AndRiskA3(feignClientData.getCustomerIncome(), feignClientData.getCustomerBusinessType()));
                    feignClientData.setR2(r2ProcessForTypeA5AndRiskA3(feignClientData.getCustomerIncome()));

                } else if(feignClientData.getCustomerType().equalsIgnoreCase("TYPE_A5")
                        && feignClientData.getCustomerType().equalsIgnoreCase("TYPE_A2")) {

                    feignClientData.setR1(r1ProcessForTypeA5orA2(feignClientData.getCustomerIncome()));
                    feignClientData.setR2(r2ProcessForTypeA5orA2(feignClientData.getCustomerIncome()));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }



            try {
                HttpConnector.postJson(Validator.FeignClientDataToJSONString(feignClientData));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return true;
    }

    private BigDecimal r1ProcessForTypeA1(String customerType, Long customerId, BigDecimal actualCustomerIncome) throws Exception{
        BigDecimal result = actualCustomerIncome.divide(BigDecimal.valueOf(10), RoundingMode.CEILING);
        double f1;

        List<FeignClientData> feignClientDataList = HttpConnector.getClientDataAsStream("/"+customerId+"/last30days");

        if(feignClientDataList.size() == 0) {
            f1 = 0.25D;
            return result.multiply(BigDecimal.valueOf(f1));
        }

        BigDecimal last30daysCustomerIncome = new BigDecimal("0");

        for(FeignClientData feignClientData: feignClientDataList) {
            last30daysCustomerIncome.add(feignClientData.getCustomerIncome());
        }

        BigDecimal average = last30daysCustomerIncome.subtract(BigDecimal.valueOf(feignClientDataList.size()));

        if(average.multiply(BigDecimal.valueOf(0.8)).compareTo(actualCustomerIncome) < 0) {
            f1 = 0.3D;
        } else {
            f1 = 0.2D;
        }
        return result.multiply(BigDecimal.valueOf(f1));
    }

    private BigDecimal r2ProcessForTypeA1(BigDecimal actualCustomerIncome, String customerBusinessType) {
        BigDecimal result = actualCustomerIncome.divide(BigDecimal.valueOf(1000), RoundingMode.CEILING);
        double f2;

        if(customerBusinessType.equals("BT_1") || customerBusinessType.equalsIgnoreCase("BR_2")) {
            f2 = 0.5D;
        } else if (customerBusinessType.equalsIgnoreCase("BR_3")) {
            f2 = 0.8D;
        } else {
            f2 = 0.9D;
        }

        return result.multiply(BigDecimal.valueOf(f2));
    }

    private BigDecimal r1ProcessForTypeA5AndRiskA3(BigDecimal customerIncome, String customerBusinessType) {
        BigDecimal result = customerIncome.divide(BigDecimal.valueOf(10), RoundingMode.CEILING);
        double f1;

        if(customerBusinessType.equalsIgnoreCase("BR_3")) {
            f1 = 0.1D;
        } else {
            f1 = 0.2D;
        }

        return result.multiply(BigDecimal.valueOf(f1));
    }

    private BigDecimal r2ProcessForTypeA5AndRiskA3(BigDecimal customerIncome) {
        return customerIncome.divide(BigDecimal.valueOf(100), RoundingMode.CEILING);
    }

    private BigDecimal r1ProcessForTypeA5orA2(BigDecimal customerIncome) {
        return customerIncome.divide(BigDecimal.valueOf(10), RoundingMode.CEILING);
    }

    private BigDecimal r2ProcessForTypeA5orA2(BigDecimal customerIncome) {
        return customerIncome.divide(BigDecimal.valueOf(100), RoundingMode.CEILING);
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
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(0)
        );
    }
}
