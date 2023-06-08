package test.web_scraping.controller;

import org.springframework.web.bind.annotation.PostMapping;
import test.web_scraping.WebScraperService;
import test.web_scraping.entity.TrEntity;
import test.web_scraping.model.TrModel;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private WebScraperService webScrapeService;

    public RestController(WebScraperService webScrapeService) {
        this.webScrapeService = webScrapeService;
    }

    @PostMapping("insert/title")
    public List<TrEntity> insertTitle(){
        return this.webScrapeService.scrapeWebsite();
    }
}
