package pl.ing.clientdatareaderschedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import pl.ing.clientdatareaderschedule.services.FeignClientDataService;
import javax.validation.Valid;

import java.util.List;

@RestController
public class FeignClientDataController {

    @Autowired
    FeignClientDataService feignClientDataService;

    @GetMapping
    public List<FeignClientData> getAllFeignClientData() {
        return feignClientDataService.getAllFeignClientData();
    }

    @PostMapping
    public FeignClientData saveFeignClientData(@RequestBody @Valid FeignClientData feignClientData) {
        return feignClientDataService.saveFeignClientData(feignClientData);
    }

    @PostMapping("/post")
    public void hehe(String data) {
        System.out.println("Hehe xd");
    }
}
