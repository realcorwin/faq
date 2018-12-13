package dik.faq;

import dik.faq.service.TestService;
import dik.faq.service.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class FaqApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaqApplication.class, args);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestServiceImpl test = context.getBean(TestServiceImpl.class);
        test.executeTest();
    }
}
