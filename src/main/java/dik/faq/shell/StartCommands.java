package dik.faq.shell;

import dik.faq.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class StartCommands {

    private TestService testService;

    @Autowired
    public StartCommands(TestService testService) {
        this.testService = testService;
    }

    @ShellMethod("Start test")
    public void start() {
        // invoke service
        testService.executeTest();
    }

}
