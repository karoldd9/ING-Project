package pl.ing.clientdatareaderschedule.validation;

import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Validator {

    private static List<String> validColumns = Arrays.asList(
            "CUSTOMER_BUSINESS_TYPE",
            "CUSTOMER_ID",
            "CUSTOMER_INCOME",
            "CUSTOMER_NAME",
            "CUSTOMER_RISK_CLASS",
            "CUSTOMER_START_DATE",
            "CUSTOMER_TYPE",
            "INFO_AS_OF_DATE"
    );

    private static List<String> stringListToUpperCase(List<String> list) {
        List<String> upperCaseList = new LinkedList<>();

        for(String value: list) {
            upperCaseList.add(value.toUpperCase());
        }

        return upperCaseList;
    }

    public static boolean validateClientData(List<String> clientData) throws Exception {
        List<List<String>> rows = splitClientDataByParameter(clientData, ";");

        List<String> missedColumns = new LinkedList<>();

        for(String validColumn: validColumns) {
            if(!stringListToUpperCase(rows.get(0)).contains(validColumn)) {
                missedColumns.add(validColumn);
            }
        }

        if(missedColumns.size() > 0) {
            //throw new Exception("Missed columns: "+ Arrays.toString(missedColumns.toArray()));
        }

        if(rows.get(0).size() > validColumns.size()) {
            throw new Exception("Too many columns!");
        }

        return true;
    }

    public static List<List<String>> splitClientDataByParameter(List<String> clientDataLines, String parameter) {
        List<List<String>> splittedClientData = new LinkedList<>();

        for(String line: clientDataLines) {
            splittedClientData.add(Arrays.stream(line.split(parameter)).toList());
        }

        return splittedClientData;
    }

    public static List<String> splitClientDataByParameter(String clientDataLine, String parameter) {

        List<String> splittedClientData = new LinkedList<>(
                Arrays.asList(
                        clientDataLine.split(parameter)
                ));

        return splittedClientData;
    }

    public static String FeignClientDataToJSONString(FeignClientData feignClientData) {

        String data = "{\n" +
                "    \"clientDataId\": "+feignClientData.getClientDataId()+",\n" +
                "    \"infoAsOfDate\": \""+feignClientData.getInfoAsOfDate()+"\",\n" +
                "    \"customerId\": "+feignClientData.getCustomerId()+",\n" +
                "    \"customerName\": \""+feignClientData.getCustomerName()+"\",\n" +
                "    \"customerStartDate\": \""+ feignClientData.getCustomerStartDate()+"\",\n" +
                "    \"customerType\": \""+feignClientData.getCustomerType()+"\",\n" +
                "    \"customerIncome\": "+feignClientData.getCustomerIncome()+",\n" +
                "    \"customerRiskClass\": \""+ feignClientData.getCustomerRiskClass()+"\",\n" +
                "    \"customerBusinessType\": \""+ feignClientData.getCustomerBusinessType()+"\",\n" +
                "    \"r1\": "+ feignClientData.getR1()+",\n" +
                "    \"r2\": "+ feignClientData.getR2()+"\n" +
                "}";

        return data;
    }
}
