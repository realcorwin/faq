package dik.faq.service;

import dik.faq.csv.CsvReader;
import dik.faq.model.Question;
import dik.faq.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//@Component
//@ConfigurationProperties("application")
@Service
public class TestServiceImpl implements TestService {

    private int count;

    @Autowired
    private ApplicationProperties applicationProperties;

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }

    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    private MessageSource messageSource;

    private CsvReader csvReader;

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public CsvReader getCsvReader() {
        return csvReader;
    }

    public TestServiceImpl(MessageSource messageSource, CsvReader csvReader) {
        this.messageSource = messageSource;
        this.csvReader = csvReader;
    }

    @Override
    public void executeTest() {

        Locale locale = Locale.ROOT;

        try(Scanner sc = new Scanner(System.in)) {

            System.out.println("Please choose your language!\nПожалуйста выберите ваш язык!\nEN or RU");

            switch (sc.nextLine().toLowerCase()){
                case "en":
                    locale = Locale.forLanguageTag("en-US");
                    break;
                case "ru":
                    locale = Locale.forLanguageTag("ru-RU");
                    break;
                default:
                    locale = Locale.getDefault();
            }

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
