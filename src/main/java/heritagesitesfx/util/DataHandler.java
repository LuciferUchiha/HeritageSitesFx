package heritagesitesfx.util;

import heritagesitesfx.models.HeritageSitesDataPM;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class DataHandler {
    private static final String FILE_NAME = "/data/heritage_sites.csv";
    private static final String DELIMITER = ";";

    public static List<HeritageSitesDataPM> readFromCSVFile() {
        try (BufferedReader reader = getReader(FILE_NAME)) {
            return reader.lines()
                    .skip(1)
                    .map(line -> new HeritageSitesDataPM(line.split(DELIMITER)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException("failed to read from csv file");
        }
    }

    public static void writeToCSVFile(List<HeritageSitesDataPM> resultate) {
        try (BufferedWriter writer = getWriter(FILE_NAME)) {
            writer.write("Category;Date inscribed;ID;Image URL;Code ISO;Location;Region;Short Description;Site;States;Visited");
            writer.newLine();
            resultate.stream()
                    .map(resultat -> resultat.infoAsLine(DELIMITER))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException("save failed");
        }
    }

    public static BufferedReader getReader(String fileName) {
        InputStream inputStream = DataHandler.class.getResourceAsStream(fileName);
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(reader);
    }

    public static BufferedWriter getWriter(String fileName) {
        try {
            String file = DataHandler.class.getResource(fileName).getFile();
            return new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("wrong file " + fileName);
        }
    }
}
