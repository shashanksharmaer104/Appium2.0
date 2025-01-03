package com.automation.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class ZoomTest {

    @Test
    public void AndroidZoomTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2); //optional
        options.setDeviceName("random-name"); //if only one device is connected
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("menu item drawing")).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(e -> e.findElement(AppiumBy.xpath("//*[@content-desc='drawing screen']")).isDisplayed());

        WebElement element = driver.findElement(AppiumBy.xpath("//*[@content-desc='drawing screen']"));

        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence1 = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement.getX() + 200, centerOfElement.getY() - 200))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement.getX() - 200, centerOfElement.getY() + 200))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        //Perform double tap
        driver.perform(Arrays.asList(sequence1, sequence2));

        Thread.sleep(2000);

        driver.quit();
    }

    @Test
    public void IOSZoomTest() throws MalformedURLException, InterruptedException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 15"); //if only one device is connected
        options.setPlatformVersion("17.0");
        options.setApp(System.getProperty("user.dir") + "/apps/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip");

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);

        WebElement element = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='tab bar option menu']"));

        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence1 = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement.getX() + 200, centerOfElement.getY() - 200))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), centerOfElement.getX() - 200, centerOfElement.getY() + 200))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


        //Perform double tap
        driver.perform(Arrays.asList(sequence1, sequence2));

        Thread.sleep(2000);

        driver.quit();
    }

    private Point getCenterOfElement(final Point location, final Dimension size) {
        return new Point(location.getX() + size.getWidth()/2, location.getY() + size.getHeight()/2);
    }

}
