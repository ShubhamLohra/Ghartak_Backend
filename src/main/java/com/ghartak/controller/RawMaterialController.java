package com.ghartak.controller;

import com.ghartak.model.RawMaterialProduct;
import com.ghartak.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
@CrossOrigin(origins = "*")
public class RawMaterialController {

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @GetMapping
    public ResponseEntity<List<RawMaterialProduct>> getAllMaterials() {
        return ResponseEntity.ok(rawMaterialRepository.findAll());
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<RawMaterialProduct>> getMaterialsByCategory(@PathVariable String cat) {
        return ResponseEntity.ok(rawMaterialRepository.findByCategory(cat));
    }
}
