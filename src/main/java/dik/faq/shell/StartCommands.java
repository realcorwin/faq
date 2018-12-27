package dik.faq.shell;

import dik.faq.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class StartCommands {

    @Autowired
    StartService startService;

    @ShellMethod("Start test")
    public void start() {
        // invoke service
        startService.startExecution();
    }

}
