package pl.ing.clientdatareaderschedule.httpConnector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import pl.ing.clientdatareaderschedule.ClientDataReaderScheduleApplication;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class HttpConnector {

    private static String localhostAddress = ClientDataReaderScheduleApplication.localhostAddress;
    private static String mailingServiceAddress = ClientDataReaderScheduleApplication.mailingServiceAddress;

    public static int postJson(String jsonString) throws Exception {

        StringRequestEntity requestEntity = new StringRequestEntity(
                jsonString,
                "application/json",
                "UTF-8");

        PostMethod postMethod = new PostMethod(localhostAddress+"/saveCustomer");
        postMethod.setRequestEntity(requestEntity);

        HttpClient httpClient = new HttpClient();

        return httpClient.executeMethod(postMethod);
    }

    public static int sendMail(String jsonString) throws Exception {

        StringRequestEntity requestEntity = new StringRequestEntity(
                jsonString,
                "application/json",
                "UTF-8");

        PostMethod postMethod = new PostMethod(mailingServiceAddress);
        postMethod.setRequestEntity(requestEntity);

        HttpClient httpClient = new HttpClient();

        return httpClient.executeMethod(postMethod);
    }

    public static List<FeignClientData> getClientData(String addressParam) throws Exception {
        URL url = new URL(localhostAddress+addressParam);

        GetMethod getMethod = new GetMethod(url.toString());

        System.out.println("Response code: "+new HttpClient().executeMethod(getMethod));

        String response = getMethod.getResponseBodyAsString();

        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        List<FeignClientData> feignClientDataList = Arrays.asList(mapper.readValue(response, FeignClientData[].class));

        for(FeignClientData feignClientData: feignClientDataList) {
            System.out.println(feignClientData);
        }

        return feignClientDataList;
    }

    public static List<FeignClientData> getClientData() throws Exception {
        return getClientData("");
    }

    public static List<FeignClientData> getClientDataAsStream(String addressParam) throws Exception {

        URL url = new URL(localhostAddress+addressParam);

        GetMethod getMethod = new GetMethod(url.toString());

        System.out.println("Response code: "+new HttpClient().executeMethod(getMethod));

        InputStream is = getMethod.getResponseBodyAsStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        String line;

        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        List<FeignClientData> feignClientDataList = new LinkedList<>();

        while ((line = bufferedReader.readLine()) != null) {
            feignClientDataList = Arrays.asList(mapper.readValue(line, FeignClientData[].class));
        }


        return feignClientDataList;
    }
}
