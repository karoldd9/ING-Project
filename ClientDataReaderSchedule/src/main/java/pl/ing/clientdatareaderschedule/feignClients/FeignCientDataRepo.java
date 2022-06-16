package pl.ing.clientdatareaderschedule.feignClients;

import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;

import java.util.List;

@FeignClient(value = "h2-db-connector")
public interface FeignCientDataRepo {
    @GetMapping
    List<FeignClientData> getAllFeignCientData();

    @PostMapping
    FeignClientData saveFeignClientData(FeignClientData feignClientData);
}
