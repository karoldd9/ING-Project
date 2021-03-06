package pl.ing.clientdatareaderschedule.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.ing.clientdatareaderschedule.processors.DataProcessor;
import pl.ing.clientdatareaderschedule.reader.DataReader;
import pl.ing.clientdatareaderschedule.validation.Validator;

import java.util.List;

@Component
public class ScheduledReader {

    @Scheduled(cron = "0 1 20 * * *")
    public void readFile() {
        DataReader dataReader = new DataReader();

        DataProcessor dataProcessor = new DataProcessor();

        try {
            List<String> fileLines = dataReader.getFileLinesByPath(
                    dataReader.getResourceFileLines("path.xml").get(0)
            );

            if(Validator.validateClientData(fileLines)) {
                dataProcessor.process(fileLines);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't find correct file by path. Loading default \"task.csv\" file...");
            try {
                List<String> fileLines = dataReader.getResourceFileLines("task.csv");
                if(Validator.validateClientData(fileLines)) {
                    dataProcessor.process(fileLines);
                }
            } catch (Exception ex) {
                e.printStackTrace();
            }
        }
    }
}
