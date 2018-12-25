package dik.faq.csv;

import dik.faq.model.Question;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties("application")
public class CsvReaderImpl implements CsvReader {

    private static final String SEPARATOR = ";";

    private String filesPath;

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    @Override
    public List<Question> getQuestions(Locale locale) throws IOException {
        return getLocaleQuestions(getFilePathFromLocale(locale));
    }

    private String getFilePathFromLocale(Locale locale) {
        String [] filesPathM = filesPath.split(",");
        Map<String, String> pathMap = new HashMap<>();
        for(String path : filesPathM){
            String [] pathTMP = path.split(":");
            pathMap.put(pathTMP[0].toLowerCase(), pathTMP[1]);
        }
        if(locale.equals(Locale.forLanguageTag("en-US"))) {
            return pathMap.get("en");
        }
        else {
            return pathMap.get("ru");
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
