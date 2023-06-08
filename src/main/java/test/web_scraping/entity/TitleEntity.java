package test.web_scraping.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "title")
public class TitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer titleId;
    private String title;
}
