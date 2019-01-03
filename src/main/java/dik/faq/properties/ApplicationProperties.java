package dik.faq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("application")
public class ApplicationProperties {

    private double testAcceptance;

    private String filesPath;

    private Map<String, String> languages = new HashMap<>();

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

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
