package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(System.getProperty("user.dir")+"\\testconfig\\configfile\\config.properties");
        Properties properties = new Properties();
        properties.load(fileReader);
    }
}
