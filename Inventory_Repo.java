package com.example.demo;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

public interface Inventory_Repo extends CrudRepository<Inventory_Entity, String>{

	Optional<Inventory_Entity> findById(long id);
 

}
