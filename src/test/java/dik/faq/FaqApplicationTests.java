package dik.faq;

import dik.faq.csv.CsvReaderImpl;
import dik.faq.service.TestServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class FaqApplicationTests {

    @Autowired
    CsvReaderImpl csvReader;

    @Autowired
    TestServiceImpl testService;

    @Test
    public void testCsvReader() {
        Assert.assertEquals(csvReader.getApplicationProperties().getFilesPath(), "RU:/faq/faqRU.csv,EN:/faq/faqEN.csv,DE:/faq/faqDE.csv");
    }

    @Test
    public void testTestServiceImpl() {
        Assert.assertNotNull(testService.getCsvReader());
        Assert.assertNotNull(testService.getMessageSource());
        Assert.assertEquals(testService.getApplicationProperties().getTestAcceptance(), 0.8, 0);

        Map<String, String> expected = new HashMap<>();
        expected.put("RU", "ru-RU");
        expected.put("EN", "en-US");
        expected.put("DE", "de-DE");
        Assert.assertEquals(testService.getApplicationProperties().getLanguages(), expected);
    }
}
