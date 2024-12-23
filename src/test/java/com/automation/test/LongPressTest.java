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
import org.openqa.selenium.interactions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class LongPressTest {

    @Test
    public void AndroidLongPressTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2); //optional
        options.setDeviceName("random-name"); //if only one device is connected
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        options.setNoReset(true);

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));

        //Get element coordinates
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofSeconds(2)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //driver.perform(Collections.singletonList(sequence));

        //or

        new Actions(driver).clickAndHold(element).perform();

        Thread.sleep(2000);

        driver.quit();
    }

    private Point getCenterOfElement(final Point location, final Dimension size) {
        return new Point(location.getX() + size.getWidth()/2, location.getY() + size.getHeight()/2);
    }

}
