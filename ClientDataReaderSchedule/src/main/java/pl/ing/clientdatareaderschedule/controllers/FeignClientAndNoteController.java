package pl.ing.clientdatareaderschedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import pl.ing.clientdatareaderschedule.feignEntities.FeignNote;
import pl.ing.clientdatareaderschedule.services.FeignClientAndNoteService;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping
public class FeignClientAndNoteController {

    @Autowired
    FeignClientAndNoteService feignClientAndNoteService;

    @GetMapping("/api/customer/getAllCustomerData")
    public List<FeignClientData> getAllFeignClientData() {
        return feignClientAndNoteService.getAllFeignClientData();
    }

    @PostMapping("/api/customer/saveCustomer")
    public FeignClientData saveFeignClientData(@RequestBody @Valid FeignClientData feignClientData) {
        return feignClientAndNoteService.saveFeignClientData(feignClientData);
    }

    @GetMapping("/api/customer/{customerId}/last30days")
    public List<FeignClientData> getFeignClientDataLast30DaysById(@PathVariable("customerId") Long id) {
        return feignClientAndNoteService.getFeignClientDataLast30DaysById(id);
    }

    @GetMapping("/api/customer/{customerId}?date={date}")
    public List<FeignClientData> getHistoricalFeignClientDataByDateAndCustomerId(@PathVariable("customerId") Long customerId, @PathVariable("date") String date) {
        return feignClientAndNoteService.getHistoricalFeignClientDataByDateAndCustomerId(customerId, date);
    }

    @GetMapping("/api/customer/{customerId}")
    public List<FeignClientData> getFeignClientDataByCustomerId(@PathVariable("customerId") Long customerId) {
        return feignClientAndNoteService.getFeignClientDataByCustomerId(customerId);
    }

    @GetMapping("/api/customer/{customerId}/note?since={since}&until={until}")
    public List<FeignNote> getFeignNoteSinceAndUntil(@PathVariable("customerId") Long customerId, @PathVariable("since") String since, @PathVariable("until") String until) {
        return feignClientAndNoteService.getFeignNoteSinceAndUntil(customerId, since, until);
    }

    @GetMapping("/api/customer/{customerId}/getAllNotes")
    public List<FeignNote> getAllFeignNotes(@PathVariable("customerId") Long id) {
        return feignClientAndNoteService.getAllFeignNotes(id);
    }

    @PostMapping("/api/customer/{customerId}/saveNote")
    public FeignNote saveFeignNote(@RequestBody @Valid FeignNote feignNote, @PathVariable("customerId") Long id) {
        return feignClientAndNoteService.saveFeignNote(feignNote, id);
    }
}
