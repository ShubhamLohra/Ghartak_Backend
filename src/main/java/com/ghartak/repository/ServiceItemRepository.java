package com.ghartak.repository;

import com.ghartak.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
    List<ServiceItem> findByCategoryId(Long categoryId);
    List<ServiceItem> findByIsPopularTrue();

    @Query("SELECT s FROM ServiceItem s WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(s.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ServiceItem> searchServices(@Param("query") String query);
}
