package dik.faq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
public class ApplicationProperties {

    private double testAcceptance;

    private String filesPath;

    public double getTestAcceptance() {
        return testAcceptance;
    }

    public void setTestAcceptance(double testAcceptance) {
        this.testAcceptance = testAcceptance;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }
}
