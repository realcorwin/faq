package dik.faq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartServiceImpl implements StartService {

    @Autowired
    TestService testService;

    @Override
    public void startExecution() {
        testService.executeTest();
    }
}
