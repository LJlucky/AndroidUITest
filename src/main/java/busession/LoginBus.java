package busession;

import handle.LoginHandle;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginBus {

    private final static Logger Log = Logger.getLogger(LoginBus.class);
    AndroidDriver driver;
    LoginHandle loginHandle;


    public LoginBus(AndroidDriver driver) {
        this.driver = driver;
        loginHandle = new LoginHandle(driver);
    }

    public void enterIntoLoginPage(){
        loginHandle.swipImage();
        loginHandle.clickMy();
        loginHandle.checkLogin();
    }


    public void LoginPage(String PhoneNumber,String errorMsg){

        loginHandle.checkPhoneNumber(PhoneNumber,errorMsg);
    }

    public void LoginMsgPage(String num,String errorMsg){

        loginHandle.checkMsg(num,errorMsg);
    }


}
