package com.yourcompany;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.evinced.appium.sdk.core.EvincedAppiumSdk;
import com.evinced.appium.sdk.core.EvincedAppiumIOSDriver;
import com.evinced.appium.sdk.core.EvincedAppiumAndroidDriver;

public class EvincedSeeTest {

    public static URL url;
    public static AndroidDriver driver;
    public static EvincedAppiumSdk a11yValidator;
    private static String accessKey = "SEETEST_KEY";


    @BeforeClass
    public static void setupAppiumDriver() throws MalformedURLException, IOException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("testName", "Evinced Test");
        dc.setCapability("accessKey", accessKey);
        dc.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
        dc.setCapability("automationName", "UIAutomator2");
        dc.setCapability("appiumVersion", "1.22.2");
        dc.setCapability("deviceName", "auto");
        dc.setCapability(MobileCapabilityType.APP, "cloud:com.example.demoapp/.MainActivity");

        driver = new AndroidDriver<>(new URL("https://cloud.seetest.io/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        a11yValidator = new EvincedAppiumSdk(driver);
        a11yValidator.setupCredentials("SERVICE_ACCOUNT_ID", "API_KEY");

    }


    @AfterClass
    public static void tearDown() {
        a11yValidator.reportStored();
        driver.quit();
    }

    @Test
    public void testStationsScreenIsAccessible() throws InterruptedException {
        a11yValidator.analyze();
    }
}
