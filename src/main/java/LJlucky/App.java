package LJlucky;

import base.InitAppium;
import busession.LoginBus;
import handle.LoginHandle;
import utils.ConfigUtil;
import java.io.FileNotFoundException;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ConfigUtil.LoadLogProperties();
//        try {
//            ConfigUtil.LoadYaml("appPage.yaml","\\src\\main\\java\\page\\","页面元素信息","elementYaml");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ConfigUtil.LoadYaml("app.yaml","\\src\\main\\java\\config\\","Driver配置信息","driverYaml");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        InitAppium a = new InitAppium();
        a.appStart();
//        a.getValue("Driver1","appActivity");
//        LoginHandle loginHandle = new LoginHandle(a.driver);
//        loginHandle.swipImage();
//        loginHandle.clickMy();

//        LoginBus loginBus = new LoginBus(a.driver);
//        loginBus.LoginPage();




    }
}
