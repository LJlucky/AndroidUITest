package testcase;

import base.InitAppium;
import busession.LoginBus;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

@Listeners({report.ZTestReport.class})
public class LoginTestCase extends InitAppium {
    AndroidDriver driver;
    LoginBus loginBus;

    @BeforeClass
    public void setDriver() {
        driver = getDriver();
        loginBus = new LoginBus(driver);
        loginBus.enterIntoLoginPage();
    }

    @DataProvider(name = "loginData")
    public Object[][] dataProvider() {
        return new Object[][]{{"1", "手机号输入有误，请重新输入"},
                {"11122223333", "手机号码格式不正确"},
                {"13916661001", ""}};
    }

    @DataProvider(name = "loginMsgData")
    public Object[][] loginMsgdataProvider() {
        return new Object[][]{{"", "验证码不能为空"}, {"1", "验证码不正确"}, {"222222", "验证码不正确"}, {"111111", ""}};
    }

    @Test(dataProvider = "loginData", description = "手机号码格式验证")
    public void loginTest001(String PhoneNumber, String errorMsg) {
        loginBus.LoginPage(PhoneNumber, errorMsg);
    }

    @Test(dataProvider = "loginMsgData", description = "验证码输入验证", dependsOnMethods = {"loginTest001"})
    public void loginTest002(String num, String errorMsg) {
        loginBus.LoginMsgPage(num, errorMsg);
    }

}
