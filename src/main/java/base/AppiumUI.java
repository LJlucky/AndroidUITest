package base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ConfigUtil;
import io.appium.java_client.TouchAction;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Map;

public class AppiumUI {
    private final static Logger Log = Logger.getLogger(AppiumUI.class);
    public static AndroidDriver driver;
    static Duration duration = Duration.ofSeconds(1);
    Map<String, Map<String, String>> ml;

    public AppiumUI(AndroidDriver driver) {
        this.driver = driver;
    }


    {
        try {
            ml = ConfigUtil.getYaml("elementYaml");
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
            System.out.println(type + ":" + value);
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
    public boolean isElementExist(By by) {
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
                    Log.info("元素存在===" + by + ">>>>>" + second);
                    break;
                }
            } catch (Exception e) {
                Log.info("在20秒内未找到元素" + by);
            }
        }
    }

    //规定时间内等待元素
    public boolean waitForAeTextAppear(By by, int timeOutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            System.out.println("OK======");
            return true;
        } catch (Exception e) {
            Log.info("规定时间内未找到元素" + by);
            return false;
        }
    }

    //根据名称 返回webElement对象
    public AndroidElement getElement(String key) {
        AndroidElement element = null;
        By by = this.getBy(key);
//        waitForElement(by);
        waitForAeTextAppear(by, 10);
        element = (AndroidElement) driver.findElement(by);
        return element;
    }

    /***
     * xpath根据content-desc查找元素
     *
     * @param view
     * @param name
     *            的内容
     * @return
     */
    public WebElement getViewbyXathwithcontentdesc(String view, String name) {
        return driver.findElementByXPath("//" + view + "[contains(@content-desc,'" + name + "')]");
    }

    /***
     * xpath根据text查找元素
     *
     * @param view
     * @param name
     * @return
     */
    public WebElement getViewbyXathwithtext(String view, String name) {
        return driver.findElementByXPath("//" + view + "[contains(@text,'" + name + "')]");
    }

    /***
     * 根据UIautomator底层方法得到对应desc的view
     *
     * @param name
     * @return View
     */
    public WebElement getViewbyUidesc(String name) {
        return driver.findElementByAndroidUIAutomator("new UiSelector().descriptionContains(\"" + name + "\")");
    }

    /***
     * 根据UIautomator底层方法得到对应text的view
     *
     * @param name
     * @return View
     */
    public WebElement getViewbyUitext(String name) {
        return driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + name + "\")");
    }

    //点击操作
    public void click(AndroidElement element) {
        element.click();
    }

    //输入操作
    public void sendKeys(AndroidElement element, String keys) {
        element.clear();
        element.sendKeys(keys);
    }

    //输入操作
    public String getText(AndroidElement element) {
        String text = null;
        text = element.getText();
        if (text != null) {
            Log.info("当前获取的Text属性值是：" + text);
        } else {
            Log.info("未获取到Text属性值");
        }
        return text;
    }

    public void assertToast(String message) {
        final WebDriverWait wait = new WebDriverWait(driver, 10);
        Assert.assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'" + message + "')]"))));
    }

    public String getToastText(String key) {
        String text = null;
        WebDriverWait wait = new WebDriverWait(driver, 1);
        try{
            WebElement target = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'" + key + "')]")));
            text = target.getText();
            Log.info(text);
        }catch (Exception e){
            System.out.println("没找到关注，继续执行");

        }

        return text;
    }


    /**
     * 左滑
     */
    public static void swipeLeft() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
//		TouchAction action = new TouchAction(driver).press(width-10,height/2)
        TouchAction action1 = new TouchAction(driver).press(PointOption.point(width - 10, height / 2)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width / 4, height / 2)).release();
        action1.perform();

    }

    /**
     * 上滑
     */
    public static void swipeUp() {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(PointOption.point(width / 2, height * 4 / 5)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width / 2, height / 4)).release();
        action1.perform();

    }

    /**
     * 下滑
     */
    public static void swipeDown(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        TouchAction action1 = new TouchAction(driver).press(PointOption.point(width / 2, height / 4)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width / 2, height * 3 / 4)).release();
        action1.perform();
    }


    /**
     * 持续点击控件
     */
    public static void keepClickElement(By by) {
        try {
            WebElement element = driver.findElement(by);
            while (true) {
                if (element.isDisplayed()) {
                    element.click();
                } else {
                    break;
                }
            }
        } catch (NoSuchElementException e) {
//            Logger.debug("未找到该控件: " + by);
        }
    }

    /**
     * 出现阻塞步骤的系统弹窗时，accept 继续
     */
    public static void acceptPermission() {
        keepClickElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().className(\"android.widget.Button\").textMatches(\".*允许.*\")"));
    }

}
