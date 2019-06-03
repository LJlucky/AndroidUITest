package busession;

import handle.LoginHandle;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;

public class LoginBus {

    private final static Logger Log = Logger.getLogger(LoginBus.class);
    AndroidDriver driver;
    LoginHandle loginHandle;

    public LoginBus(AndroidDriver driver) {
        this.driver = driver;
        loginHandle = new LoginHandle(driver);
    }

    public void LoginPage(){
        loginHandle.swipImage();
        loginHandle.clickMy();
        loginHandle.checkLogin();
        loginHandle.checkPhoneNumber("11916661001");
    }


}
