package com.ghartak.repository;

import com.ghartak.model.RawMaterialProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterialProduct, Long> {
    List<RawMaterialProduct> findByCategory(String category);
}
