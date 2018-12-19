package dik.faq.csv;

import dik.faq.model.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@PropertySource("classpath:application.properties")
@Component
public class CsvReaderImpl implements CsvReader {

    private static final String SEPARATOR = ";";

    private String csvFilePathRU;
    private String csvFilePathEN;

    public CsvReaderImpl(@Value(value = "${filePathRU}") String csvFilePathRU, @Value("${filePathEN}") String csvFilePathEN) {
        this.csvFilePathRU = csvFilePathRU;
        this.csvFilePathEN = csvFilePathEN;
    }

    @Override
    public List<Question> getQuestions(Locale locale) throws IOException {
        if(locale.equals(Locale.forLanguageTag("en-US"))){
            return getLocaleQuestions(csvFilePathEN);
        }
        else {
            return getLocaleQuestions(csvFilePathRU);
        }
    }

    public List<Question> getLocaleQuestions(String csvFilePath) throws IOException {

        File file = new File(URLDecoder.decode(String.valueOf(this.getClass().getResource(csvFilePath).getFile())));
        Path path = Paths.get(String.valueOf(file));
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
