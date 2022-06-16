package pl.ing.clientdatareaderschedule.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ing.clientdatareaderschedule.feignClients.FeignCientDataRepo;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;

import java.util.List;

@Service
public class FeignClientDataService {

    @Autowired
    FeignCientDataRepo feignCientDataRepo;

    public List<FeignClientData> getAllFeignClientData() {
        return feignCientDataRepo.getAllFeignCientData();
    }

    public FeignClientData saveFeignClientData(FeignClientData feignClientData) {
        return feignCientDataRepo.saveFeignClientData(feignClientData);
    }
}
