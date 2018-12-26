package dik.faq;

import dik.faq.csv.CsvReaderImpl;
import dik.faq.service.TestServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FaqApplicationTests {

    @Autowired
    CsvReaderImpl csvReader;

    @Autowired
    TestServiceImpl testService;

    @Test
    public void testCsvReader() {
        Assert.assertEquals(csvReader.getApplicationProperties().getFilesPath(), "RU:/faq/faqRU.csv,EN:/faq/faqEN.csv");
    }

    @Test
    public void testTestServiceImpl() {
        Assert.assertNotNull(testService.getCsvReader());
        Assert.assertNotNull(testService.getMessageSource());
        Assert.assertEquals(testService.getApplicationProperties().getTestAcceptance(), 0.8, 0);
    }
}
