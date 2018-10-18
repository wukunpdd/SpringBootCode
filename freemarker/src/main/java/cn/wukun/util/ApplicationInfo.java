package cn.wukun.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInfo {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${generating.address}")
    private String generatingAddress;

    @Value("${package.name}")
    private String packageName;

    @Value("${template.address}")
    private String templateAddress;

    public String getDriverClass() {
        return driverClass;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGeneratingAddress() {
        return generatingAddress;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getTemplateAddress() {
        return templateAddress;
    }
}
