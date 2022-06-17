package pl.ing.clientdatareaderschedule.httpConnector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HttpConnector {
    public static int postJson(String jsonString) throws Exception {

        StringRequestEntity requestEntity = new StringRequestEntity(
                jsonString,
                "application/json",
                "UTF-8");

        PostMethod postMethod = new PostMethod("http://localhost:8080");
        postMethod.setRequestEntity(requestEntity);

        HttpClient httpClient = new HttpClient();

        return httpClient.executeMethod(postMethod);
    }

    public static void getClientData() throws Exception {
        URL url = new URL("http://localhost:8080");

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
    }
}
