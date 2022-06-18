package pl.ing.clientdatareaderschedule.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import pl.ing.clientdatareaderschedule.feignEntities.FeignNote;

import java.util.List;

@FeignClient(value = "h2-db-connector")
public interface FeignClientAndNoteRepo {
    @GetMapping("/api/customer/getAllCustomerData")
    List<FeignClientData> getAllFeignCientData();

    @PostMapping("/api/customer/saveCustomer")
    FeignClientData saveFeignClientData(FeignClientData feignClientData);

    @GetMapping("/api/customer/{customerId}/last30days")
    List<FeignClientData> getFeignClientDataLast30DaysByCustomerId(@PathVariable("customerId") Long customerId);

    @GetMapping("api/customer/{customerId}?date={date}")
    List<FeignClientData> getHistoricalFeignClientDataByDateAndCustomerId(@PathVariable("customerId") Long customerId, @PathVariable("date") String date);

    @GetMapping("/api/customer/{customerId}")
    List<FeignClientData> getFeignClientDataByCustomerId(@PathVariable("customerId") Long customerId);

    @GetMapping("/api/customer/{customerId}/note?since={since}&until={until}")
    List<FeignNote> getFeignNoteSinceAndUntil(@PathVariable("customerId") Long customerId, @PathVariable("since") String since, @PathVariable("until") String until);

    @GetMapping("/api/customer/{customerId}/getAllNotes")
    List<FeignNote> getAllFeignNotes(@PathVariable("customerId") Long customerId);

    @PostMapping("/api/customer/{customerId}/saveNote")
    FeignNote saveFeignNote(FeignNote feignNote, @PathVariable("customerId") Long id);
}