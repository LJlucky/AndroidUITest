package handle;

import base.AppiumUI;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.testng.Assert;

public class LoginHandle extends AppiumUI {
    private final static Logger Log = Logger.getLogger(LoginHandle.class);

    public LoginHandle(AndroidDriver driver) {
        super(driver);
    }

    //滑动广告页面
    public void swipImage() {
        AndroidElement image = getElement("image");
        for (int i = 0; i < 3; i++) {
            swipeLeft();
        }
        image.click();
    }

    //验证是否有升级弹窗，选择不升级，点击【我的】
    public void clickMy() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isElementExist(getBy("upgrade_title"))) {
            Log.info("存在升级弹窗，进行取消操作");
            AndroidElement upgrade_cancel = getElement("upgrade_cancel");
            click(upgrade_cancel);
        }
        AndroidElement tabText_wode = getElement("tabText_wode");
        Log.info("点击【我的】，进入个人中心");
        click(tabText_wode);
    }

    //检查当前是否登录
    public void checkLogin() {
        if (isElementExist(getBy("not_login_tip"))) {
            AndroidElement login_btn = getElement("login_btn");
            click(login_btn);
        } else if (isElementExist(getBy("user_name"))) {
            AndroidElement user_name = getElement("user_name");
            getText(user_name);
            Log.info("进行退出登录操作");
            outLogin();
        }
    }

    //退出登录操作
    private void outLogin() {
        AndroidElement user_bg = getElement("user_bg");
        click(user_bg);
        if (isElementExist(getBy("user_phone_no_text"))) {
            Log.info("进入【个人设置】页面");
            AndroidElement user_phone_no_text = getElement("user_phone_no_text");
            getText(user_phone_no_text);
            AndroidElement user_exit_text = getElement("user_exit_text");
            click(user_exit_text);
            if (isElementExist(getBy("loading_layout"))) {
                AndroidElement outLogin_left_btn = getElement("outLogin_left_btn");
                click(outLogin_left_btn);
                if (isElementExist(getBy("not_login_tip"))) {
                    Log.info("退出登录操作成功，当前是【我的】页面");
                } else {
                    Log.info("退出登录操作失败");
                }
            } else {
                Log.info("点击[退出登录]按钮失败");
            }
        } else {
            Log.info("进入【个人设置】页面-失败");
        }
    }

    //手机号码格式验证
    public void checkPhoneNumber(String PhoneNumber, String errorMsg) {
        AndroidElement input_phone_ed = getElement("input_phone_ed");
        sendKeys(input_phone_ed, PhoneNumber);
        AndroidElement btn_msg_get_code = getElement("btn_msg_get_code");
        click(btn_msg_get_code);
        String text1 = getToastText("验证码已成功发送");
//        getToastText("验证码已成功发送");
        if (text1 != null) {
            System.out.println("登录成功！！！！");
        } else {
            AndroidElement login_error = getElement("login_error");
            String text = getText(login_error);
            Assert.assertEquals(text, errorMsg, "验证手机号码格式");
        }
    }

    public void checkMsg(String num, String errorMsg){
        AndroidElement input_msg_code = getElement("input_msg_code");
        if(num != null){
            sendKeys(input_msg_code,num);
            click(getElement("msg_login_btn"));
        }else {
            click(getElement("msg_login_btn"));
        }

        if(isElementExist(getBy("qr_code_sign"))){
            Log.info("登录成功！！！");
        }else{
            AndroidElement login_error = getElement("login_error");
            String text = getText(login_error);
            Assert.assertEquals(text, errorMsg, "验证手机验证码");
        }
    }

}
