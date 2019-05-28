package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import utils.ConfigUtil;
import utils.test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class InitAppium {
    private AndroidDriver driver;

    @BeforeSuite
    public void appStart() {
        ConfigUtil.LoadLogProperties();
        try {
            ConfigUtil.LoadYaml("appPage.yaml","\\src\\main\\java\\page\\","页面元素信息");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ConfigUtil.LoadYaml("app.yaml","\\src\\main\\java\\config\\","Driver配置信息");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();

//        capabilities.setCapability("platformVersion", "5.1");
//        capabilities.setCapability("deviceName", "MEIZU_MX5");
//        capabilities.setCapability("udid", "85OBBM6224MV");
//
//        capabilities.setCapability("appPackage", "com.hrcfc.hrApp.custManager");
//        capabilities.setCapability("appActivity", ".base.LaunchActivity_");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("unicodeKeyboard", "true");


        capabilities.setCapability("platformVersion", getValue("Driver1","platformVersion"));
        capabilities.setCapability("deviceName", getValue("Driver1","deviceName"));
        capabilities.setCapability("udid", getValue("Driver1","udid"));

        capabilities.setCapability("appPackage", getValue("Driver1","appPackage"));
        capabilities.setCapability("appActivity", getValue("Driver1","appActivity"));
        capabilities.setCapability("platformName", getValue("Driver1","platformName"));
        capabilities.setCapability("unicodeKeyboard", getValue("Driver1","unicodeKeyboard"));


        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public AndroidDriver getDriver() {
        if (driver != null) {
            System.out.println("driver创建成功！！！");
        } else {
            System.out.println("driver创建失败！！！");
        }
        return driver;
    }

     Map<String, Map<String, String>> ml = null;

    {
        try {
            ml = ConfigUtil.getYaml();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  String getValue(String driver,String key){
        String value = null;
        if (ml.containsKey(driver)) {
            Map<String, String> m = ml.get(driver);
            value = m.get(key);
            System.out.println(value);
        }
        return value;
    }

}
