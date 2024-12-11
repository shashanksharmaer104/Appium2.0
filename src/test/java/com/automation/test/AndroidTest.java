package com.automation.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

class AndroidTest {

    @Test
    public void AndroidLaunchTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2); //optional
        options.setDeviceName("random-name"); //if only one device is connected
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(5000);
    }

    @Test
    public void iOSLaunchTest() throws MalformedURLException, InterruptedException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 15"); //if only one device is connected
        options.setPlatformVersion("17.0");
        options.setApp(System.getProperty("user.dir") + "/apps/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip");

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
        Thread.sleep(5000);
    }

}
