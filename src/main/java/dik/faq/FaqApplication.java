package dik.faq;

import dik.faq.service.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FaqApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaqApplication.class, args);
        // ApplicationContext context = SpringApplication.run(FaqApplication.class, args);
        // TestServiceImpl test = context.getBean(TestServiceImpl.class);
        // test.executeTest();
    }
}
