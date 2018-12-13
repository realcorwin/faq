package dik.faq.csv;

import dik.faq.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReaderImpl implements CsvReader {

    private static final String SEPARATOR = ";";

    private String csvFilePath;

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public CsvReaderImpl(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public CsvReaderImpl() {
    }

    @Override
    public List<Question> getQuestions() throws IOException {

        Path path = Paths.get("csvFilePath");
        if (Files.exists(path)) {

            List<String> lines = Files.readAllLines(path);

            return lines.stream()
                    .map(line -> line.split(SEPARATOR))
                    .map(array -> new Question(array[0],array[1]))
                    .collect(Collectors.toList());
        }
        return new ArrayList<Question>();
    }
}
