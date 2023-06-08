package test.web_scraping;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import test.web_scraping.entity.TrEntity;
import test.web_scraping.model.TrModel;
import test.web_scraping.repository.TitleRepository;
import test.web_scraping.repository.TrRepository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@Service
public class WebScraperService {
    private TitleRepository titleRepository;
    private TrRepository trRepository;

    public WebScraperService(TitleRepository titleRepository, TrRepository trRepository) {
        this.titleRepository = titleRepository;
        this.trRepository = trRepository;
    }

    public List<TrEntity> scrapeWebsite(){

        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String URL = "https://www.set.or.th/th/market/product/stock/quote/7UP/historical-trading";

        List<TrEntity> result = new ArrayList<>();

        try{
            driver.get(URL);
            int a = 0;
            while (a<6){

                Thread.sleep(6000);
                WebElement nextButton = null;
                int attempts = 0;
                while (nextButton == null && attempts < 10) {
                    try {
                        nextButton = driver.findElement(By.cssSelector("button[aria-label='Go to next page']"));
                        List<WebElement> trList = driver.findElements(By.tagName("tr"));

                        List<WebElement> status = driver.findElements(By.cssSelector(".market-close"));
                        WebElement name = driver.findElement(By.tagName("h1"));

                        String titleName = name.getText();
                        WebElement titleStatus = status.get(0);
                        String hhh = titleStatus.getText();
//                        System.out.println(hhh);

                        int tr = trList.size();
                        for (int i=0; i<tr; i++){
                            WebElement value = trList.get(i);
                            String myTitle = value.getText();
                            if (i<12){
                                continue;
                            }else {
                                String[] myTitles = myTitle.split(" ");
                                DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                                        .appendPattern("dd-LLL-yyyy")
                                        .parseCaseInsensitive()
                                        .parseLenient()
                                        .toFormatter(new Locale("th", "TH"));

                                String all = myTitles[0] + "-" + myTitles[1] + "-" + myTitles[2];
                                LocalDate formattedDate = LocalDate.parse(all, formatter);
                                TrEntity v = new TrEntity();
                                v.setSYMBOL(titleName);
                                v.setTRANSACTION_DATE(formattedDate.atStartOfDay());
                                v.setOPEN_PRICE(Double.valueOf(myTitles[3]));
                                v.setMAX_PRICE(Double.valueOf(myTitles[4]));
                                v.setMIN_PRICE(Double.valueOf(myTitles[5]));
                                v.setCLOSE_PRICE(Double.valueOf(myTitles[6]));
                                v.setCHANGE_PRICE(Double.valueOf(myTitles[7]));
                                v.setCHANGE_RATIO(Double.valueOf(myTitles[8]));
                                v.setSTATUS("Closed");
                                v.setVOLUME(Double.valueOf(myTitles[9].replace(",", "")));
                                v.setBATCH_ID(BigInteger.valueOf(Long.parseLong(myTitles[10].replace(",", "").replace(".", ""))));
                                this.trRepository.save(v);
                                result.add(v);
                            }
                        }
                    } catch (Exception e) {
                        Thread.sleep(500);
                        attempts++;
                    }

                }
                if (nextButton != null) {
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", nextButton);
                } else {
                    System.out.println("Element not found. Exiting loop.");
                    break;
                }
                Thread.sleep(3000);

                a++;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
        return result;
    }
}
