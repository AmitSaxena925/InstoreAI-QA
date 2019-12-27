package com.capillary.VisitorMatrix.qa.framework.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

    private static List<WebDriverThread> webDriverThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverThread>());
    
    private static List<WebDriverWait> webDriverwaitThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverWait>());

    private static ThreadLocal<WebDriverThread> driverThread = new ThreadLocal<WebDriverThread>(){
        @Override
        protected WebDriverThread initialValue(){
            WebDriverThread webDriverThread = new WebDriverThread();
            webDriverThreadPool.add(webDriverThread);
            return webDriverThread;
        }
    };

    private static ThreadLocal<WebDriverWait> waitThread = new ThreadLocal<WebDriverWait>(){
        @Override
        protected WebDriverWait initialValue(){
            WebDriverWait wait = new WebDriverWait(getDriver(), ExecutionConfig.WAIT_TIME);
            webDriverwaitThreadPool.add(wait);
            return wait;
        }
    };

    public static WebDriver getDriver(){
        return driverThread.get().getDriver();
    }

    public static WebDriverWait getWait(){
        return waitThread.get();
    }

    public static void setWaitTime(int seconds){
        waitThread.set(new WebDriverWait(getDriver(), seconds));
    }

    public static void resetWaitTime(){
        waitThread.set(new WebDriverWait(getDriver(),ExecutionConfig.WAIT_TIME));
    }

    public static void closeDriverObjects() throws InterruptedException{
        for(WebDriverThread webDriverThread: webDriverThreadPool){
            webDriverThread.quitDriver();
            waitThread.remove();
        }        
    }
    public static void closeDriverObject() throws InterruptedException
    {
    	driverThread.get().quitDriver();
    	waitThread.remove();
    }
}