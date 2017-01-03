package org.lzh.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 携程优惠信息
 *
 * @author lzh
 * @create 2017-01-03 11:49
 */
public class Xiecheng {
    static {
        System.setProperty("webdriver.chrome.driver", "D:\\software\\installs\\chromedriver.exe");
    }

    public static void main(String[] args) {
        Xiecheng test = new Xiecheng();
        String url = "http://hotels.ctrip.com/hotel/6066094.html";
        test.findYouhui(url);
    }

    private void findYouhui(String url){
        //http://hotels.ctrip.com/hotel/6066094.html
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("url html: " + webDriver.getPageSource());
        //*[@id="hotel_tabs_DP_TAB"]/li[2]
        WebElement clickElement = webDriver.findElement(By.xpath("//*[@id=\"hotel_tabs_DP_TAB\"]/li[2]"));
        clickElement.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("url html: " + webDriver.getPageSource());
        //*[@id="divInsuranceWrap"]/ul/li[1]/div/div[1]
        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"divInsuranceWrap\"]/ul/li[1]/div/div[1]"));
        System.out.println(element.getText().toString());
        webDriver.close();
    }
}
