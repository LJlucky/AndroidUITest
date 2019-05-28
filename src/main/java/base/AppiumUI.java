package base;

import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.ConfigUtil;

import java.io.FileNotFoundException;
import java.util.Map;

public class AppiumUI {
    private final static Logger Log = Logger.getLogger(AppiumUI.class);
    public AndroidDriver driver;
    Map<String, Map<String, String>> ml;

    {
        try {
            ml = ConfigUtil.getYaml();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //创建by对象
    public By getBy(String key) {
        By by = null;
        if (ml.containsKey(key)) {
            Map<String, String> m = ml.get(key);
            String type = m.get("type");
            String value = m.get("value");
            switch (type) {
                case "id":
                    by = By.id(value);
                    break;
                case "name":
                    by = By.name(value);
                    break;
                case "xpath":
                    by = By.xpath(value);
                    break;
                case "class":
                    by = By.className(value);
                    break;
                case "linkText":
                    by = By.linkText(value);
                    break;
                case "cssSelector":
                    by = By.cssSelector(value);
                    break;
            }
        } else {
            System.out.println("Locator " + key + " is not exist in  ");
        }
        return by;
    }

    /*
     *判断元素是否存在
     *
     */
    private boolean isElementExist(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    //规定时间内等待元素
    protected void waitForElement(By by) {
        for (int second = 0; ; second++) {
            if (second > 20) {
                Log.info(by + "元素加载失败");
            }
            try {
                if (isElementExist(by)) {
//                    Log.info("元素存在===" + by + ">>>>>" + second);
                    break;
                }
            } catch (Exception e) {
                Log.info("在20秒内未找到元素" + by);
            }
        }
    }

    //根据名称 返回webElement对象
    public  WebElement getElement(String key) {
        WebElement element = null;
        By by = this.getBy(key);
        waitForElement(by);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element = driver.findElement(by);
        return element;
    }
}
