package pl.ing.clientdatareaderschedule.reader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DataReader {

    public List<String> getResourceFileLines(String resourceFileName) throws Exception {
        Resource resource = new ClassPathResource(resourceFileName);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bfReader = new BufferedReader(inputStreamReader);

        String line;

        List<String> fileLines = new LinkedList<>();

        while((line = bfReader.readLine()) != null) {
            fileLines.add(line);
        }

        return fileLines;
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
