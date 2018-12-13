package dik.faq.service;

import dik.faq.csv.CsvReader;
import dik.faq.model.Question;

import java.util.List;
import java.util.Scanner;

public class TestServiceImpl implements TestService {

    private int count;

    private CsvReader csvReader;

    public TestServiceImpl(CsvReader csvReader) {

        this.csvReader = csvReader;
    }

    @Override
    public void executeTest() {

        String firstName, secondName;

        try(Scanner sc = new Scanner(System.in)) {
            List<Question> questions = csvReader.getQuestions();
            System.out.println("Ваша фамилия?");
            secondName = sc.nextLine();
            System.out.println("А имя?");
            firstName = sc.nextLine();
            String name = firstName + " " + secondName;
            System.out.println(name + ", ответьте, пожалуйста, на вопросы!");
            for (Question question : questions) {
                System.out.println(question.getQuestion());
                if (sc.nextLine().equalsIgnoreCase(question.getAnswer())) {
                    count++;
                }
            }
            if (count >= (questions.size() * 80) / 100) {
                System.out.println(name + ", вы сдали тест!");
            } else {
                System.out.println(name + ", вы не сдали тест!");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
