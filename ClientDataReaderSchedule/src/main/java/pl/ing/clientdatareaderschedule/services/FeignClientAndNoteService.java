package pl.ing.clientdatareaderschedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ing.clientdatareaderschedule.feignClients.FeignClientAndNoteRepo;
import pl.ing.clientdatareaderschedule.feignEntities.FeignClientData;
import pl.ing.clientdatareaderschedule.feignEntities.FeignNote;

import java.util.List;

@Service
public class FeignClientAndNoteService {

    @Autowired
    FeignClientAndNoteRepo feignClientAndNoteRepo;

    public List<FeignClientData> getAllFeignClientData() {
        return feignClientAndNoteRepo.getAllFeignCientData();
    }

    public FeignClientData saveFeignClientData(FeignClientData feignClientData) {
        return feignClientAndNoteRepo.saveFeignClientData(feignClientData);
    }

    public List<FeignClientData> getFeignClientDataLast30DaysById(Long id) {
        return feignClientAndNoteRepo.getFeignClientDataLast30DaysByCustomerId(id);
    }

    public List<FeignClientData> getHistoricalFeignClientDataByDateAndCustomerId(Long id, String date) {
        return feignClientAndNoteRepo.getHistoricalFeignClientDataByDateAndCustomerId(id, date);
    }

    public List<FeignClientData> getFeignClientDataByCustomerId(Long id) {
        return feignClientAndNoteRepo.getFeignClientDataByCustomerId(id);
    }

    public List<FeignNote> getFeignNoteSinceAndUntil(Long customerId, String since, String until) {
        return feignClientAndNoteRepo.getFeignNoteSinceAndUntil(customerId, since, until);
    }

    public List<FeignNote> getAllFeignNotes(Long id) {
        return feignClientAndNoteRepo.getAllFeignNotes(id);
    }

    public FeignNote saveFeignNote(FeignNote feignNote, Long customerId) {
        return feignClientAndNoteRepo.saveFeignNote(feignNote, customerId);
    }
}
