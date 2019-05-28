package LJlucky;

import base.AppiumUI;
import base.InitAppium;
import org.openqa.selenium.WebElement;
import utils.ConfigUtil;
import utils.test;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            ConfigUtil.LoadYaml("app.yaml","\\src\\main\\java\\config\\","Driver配置信息");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InitAppium a = new InitAppium();
        a.appStart();
        a.getValue("Driver1","appActivity");


//        System.out.println(System.getProperty("user.dir"));
//        System.out.println("配置文件初始化");
//        System.out.println("(1)开始加载log4j.prpperties配置文件");
//        ConfigUtil.LoadLogProperties();
//        try {
//            ConfigUtil.LoadYaml("appPage.yaml");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        AppiumUI b = new AppiumUI();
//        System.out.println(b.getBy("loginName"));


//        try {
//            ConfigUtil.LoadYaml("app.yaml", "\\src\\main\\java\\config\\", "Driver配置信息");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        Map<String, Map<String, String>> ml = null;
//
//        {
//            try {
//                ml = ConfigUtil.getYaml();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (ml.containsKey("Driver1")) {
//            Map<String, String> m = ml.get("Driver1");
//            String type = m.get("appPackage");
//            System.out.println(type);
//        }
//        System.out.println(ConfigUtil.getStringValue("Driver1", "appPackage"));

//        test b = new test();
//        b.getValue("Driver1","appActivity");
    }
}
