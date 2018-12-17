package dik.faq.service;

import dik.faq.csv.CsvReader;
import dik.faq.model.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@PropertySource("classpath:application.properties")
@Service
public class TestServiceImpl implements TestService {

    private int count;

    private double testAcceptance;

    private MessageSource messageSource;

    private CsvReader csvReader;

    public TestServiceImpl(@Value("${testAcceptance}") double testAcceptance, MessageSource messageSource, CsvReader csvReader) {
        this.testAcceptance = testAcceptance;
        this.messageSource = messageSource;
        this.csvReader = csvReader;
    }

    @Override
    public void executeTest() {

        Locale locale = Locale.ENGLISH;

        try(Scanner sc = new Scanner(System.in)) {

            System.out.println("Please choose your language!\nПожалуйста выберите ваш язык!\nEN or RU");
            locale.setDefault(getLocale(sc.nextLine().toLowerCase()));

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

    private Locale getLocale(String localeS){
        Locale locale = null;
        switch (localeS){
            case "en":
                locale = Locale.ENGLISH;
                break;
            case "ru":
                locale = Locale.getDefault();
                break;
            default:
                locale = Locale.getDefault();
        }
        return locale;
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
        if (count >= (int)(questions.size() * testAcceptance)) {
            System.out.println(messageSource.getMessage("faq.good", new String[]{name}, locale));
        } else {
            System.out.println(messageSource.getMessage("faq.bad", new String[]{name}, locale));
        }
    }
}
