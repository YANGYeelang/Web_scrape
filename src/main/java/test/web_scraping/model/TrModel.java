package test.web_scraping.model;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class TrModel {
    private String SYMBOL;
    private LocalDateTime TRANSACTION_DATE;
    private Double OPEN_PRICE;
    private Double MAX_PRICE;
    private Double MIN_PRICE;
    private Double CLOSE_PRICE;
    private Double CHANGE_PRICE;
    private Double CHANGE_RATIO;
    private Double NO_OF_STOCK;
    private Double VOLUME;
    private String REASON;
    private String STATUS;
    private BigInteger BATCH_ID;
}
