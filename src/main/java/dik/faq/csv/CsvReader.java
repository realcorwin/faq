package dik.faq.csv;

import dik.faq.model.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public interface CsvReader {
    public List<Question> getQuestions(Locale locale) throws IOException;
}
