package pl.ing.clientdatareaderschedule.feignClients;

import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;

import java.util.List;

@FeignClient(value = "h2-db-connector")
public interface FeignCientDataRepo {
    @GetMapping("/api/customer/getAllCustomerData")
    List<FeignClientData> getAllFeignCientData();

    @PostMapping("/api/customer")
    FeignClientData saveFeignClientData(FeignClientData feignClientData);

    @GetMapping("/api/customer/{customerId}/last30days")
    List<FeignClientData> getFeignClientDataLast30DaysByCustomerId(@PathVariable("customerId") Long customerId);
}
