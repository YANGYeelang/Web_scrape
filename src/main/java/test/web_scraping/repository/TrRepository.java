package test.web_scraping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.web_scraping.entity.TrEntity;

public interface TrRepository extends JpaRepository<TrEntity, Integer> {
}
