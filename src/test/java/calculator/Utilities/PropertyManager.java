package calculator.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//**********************************************************************************************************
//Description: PropertyManager class reads global configurations and use them during test execution.
//**********************************************************************************************************
public class PropertyManager {

    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir") +
            "\\src\\test\\resources\\calculator\\config.properties";

    //Create a Singleton instance. We need only one instance of Property Manager.
    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
            }
        }
        return instance;
    }


    //Get configuration data for key
    private String loadData(String key) {
        //Declare a properties object
        Properties prop = new Properties();

        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
        return prop.getProperty(key);
    }

    public String getProperty(String key) {
        return loadData(key);
    }

}
