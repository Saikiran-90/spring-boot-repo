package com.example.demo;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchJpa extends JpaRepository<Inventory_Entity, String>{
   
	@Query("select en from Inventory_Entity en where en.product_name= ?1")
	List<Inventory_Entity> findByNameIgnoreCase(String key);
}
