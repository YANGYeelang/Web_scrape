package test.web_scraping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.web_scraping.entity.TitleEntity;
@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Integer> {
}
