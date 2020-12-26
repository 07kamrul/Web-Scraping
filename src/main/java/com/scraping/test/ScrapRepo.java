package com.scraping.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepo extends JpaRepository<ScrapModel, Long> {
}
