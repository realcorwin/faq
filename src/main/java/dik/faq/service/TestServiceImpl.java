package dik.faq.service;

import dik.faq.csv.CsvReader;
import dik.faq.model.Question;
import dik.faq.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestServiceImpl implements TestService {

    private int count;

    private Map<String, String> languagesForChoose = new HashMap<>();

    @Autowired
    private ApplicationProperties applicationProperties;

    private MessageSource messageSource;

    private CsvReader csvReader;

    public CsvReader getCsvReader() {
        return csvReader;
    }

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    private Scanner sc;

    public TestServiceImpl(MessageSource messageSource, CsvReader csvReader) {
        this.messageSource = messageSource;
        this.csvReader = csvReader;
    }

    @Override
    public void executeTest() {

        Locale locale = Locale.ROOT;

        languagesForChoose = applicationProperties.getLanguages();

        // String languageTag = "ru-RU";

        if(sc == null){
            sc = new Scanner(System.in);
        }
        try {

            System.out.println("List of available languages - Список доступных языков");
            for (String language : languagesForChoose.keySet()) {
                System.out.print(language + " / ");
            }

            System.out.println("\nPlease choose your language!\nПожалуйста выберите ваш язык!");

            // languageTag = Optional.ofNullable(languagesForChoose.get(sc.nextLine().toUpperCase())).orElse("ru-RU");

            locale = Locale.forLanguageTag(Optional.ofNullable(languagesForChoose.get(sc.nextLine().toUpperCase())).orElse("ru-RU"));

            List<Question> questions = csvReader.getQuestions(locale);

            String name = getFullName(sc, locale);

            System.out.println(messageSource.getMessage("faq.questions", new String[]{name}, locale));
            for (Question question : questions) {
                System.out.println(question.getQuestion());
                if (sc.nextLine().equalsIgnoreCase(question.getAnswer())) {
                    count++;
                }
            }

            resultsOutput(questions, name, locale);
        }

        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getFullName(Scanner sc, Locale locale) {
        String secondName;
        String firstName;
        System.out.println(messageSource.getMessage("second.name", null, locale));
        secondName = sc.nextLine();
        System.out.println(messageSource.getMessage("first.name", null, locale));
        firstName = sc.nextLine();
        return firstName + " " + secondName;
    }

    private void resultsOutput(List<Question> questions, String name, Locale locale) {
        if (count >= (int)(questions.size() * applicationProperties.getTestAcceptance())) {
            System.out.println(messageSource.getMessage("faq.good", new String[]{name}, locale));
        } else {
            System.out.println(messageSource.getMessage("faq.bad", new String[]{name}, locale));
        }
    }
}
