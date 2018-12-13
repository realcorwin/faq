package dik.faq.csv;

import dik.faq.model.Question;

import java.io.IOException;
import java.util.List;

public interface CsvReader {
    public List<Question> getQuestions() throws IOException;
}
