package pl.ing.clientdatareaderschedule.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import pl.ing.clientdatareaderschedule.ClientDataReaderScheduleApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataReader {

    public List<String> getResourceFileLines(String resourceFileName) throws Exception {

        return getFileLinesByPath("ClientDataReaderSchedule\\src\\main\\resources\\"+resourceFileName);
    }

    public List<String> getFileLinesByPath(String filePath) throws Exception {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        List<String> fileLines = new LinkedList<>();

        while (scanner.hasNextLine()) {
            fileLines.add(scanner.nextLine());
        }

        return fileLines;
    }
}
