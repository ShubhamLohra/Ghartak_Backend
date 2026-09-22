package com.ghartak.repository;

import com.ghartak.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    
    @Query("SELECT DISTINCT c FROM ServiceCategory c LEFT JOIN FETCH c.services ORDER BY c.id ASC")
    List<ServiceCategory> findAllWithServices();

    Optional<ServiceCategory> findByCode(String code);
    Optional<ServiceCategory> findByName(String name);
}
