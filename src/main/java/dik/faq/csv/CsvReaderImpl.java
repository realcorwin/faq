package dik.faq.csv;

import dik.faq.model.Question;
import dik.faq.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CsvReaderImpl implements CsvReader {

    private static final String SEPARATOR = ";";

    @Autowired
    private ApplicationProperties applicationProperties;

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }

    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public List<Question> getQuestions(Locale locale) throws IOException {
        return getLocaleQuestions(getFilePathFromLocale(locale));
    }

    private String getFilePathFromLocale(Locale locale) {
        String [] filesPathM = applicationProperties.getFilesPath().split(",");
        Map<String, String> pathMap = new HashMap<>();
        for(String path : filesPathM){
            String [] pathTMP = path.split(":");
            pathMap.put(pathTMP[0].toLowerCase(), pathTMP[1]);
        }

        String lang = locale.getLanguage();
        return pathMap.get((lang == null || lang.isEmpty())? "ru": lang.toLowerCase());
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
