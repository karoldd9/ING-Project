package pl.ing.clientdatareaderschedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import pl.ing.clientdatareaderschedule.services.FeignClientDataService;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class FeignClientDataController {

    @Autowired
    FeignClientDataService feignClientDataService;

    @GetMapping("/getAllClientData")
    public List<FeignClientData> getAllFeignClientData() {
        return feignClientDataService.getAllFeignClientData();
    }

    @PostMapping
    public FeignClientData saveFeignClientData(@RequestBody @Valid FeignClientData feignClientData) {
        return feignClientDataService.saveFeignClientData(feignClientData);
    }

    @GetMapping("/{customerId}/last30days")
    public List<FeignClientData> getFeignClientDataLast30DaysById(@PathVariable("customerId") Long id) {
        return feignClientDataService.getFeignClientDataLast30DaysById(id);
    }
}
