package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BassClass {
    WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void setup(String browser, String url) throws IOException {
        logger = LogManager.getLogger(this.getClass());
        FileReader file = new FileReader("src//test//java//utils//ReadFromExcel.java");
        properties.load(file);

        switch (browser.toLowerCase()){
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                System.out.println("Invalid option");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
