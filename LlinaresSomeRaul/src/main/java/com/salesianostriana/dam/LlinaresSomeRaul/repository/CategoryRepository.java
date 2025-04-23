package com.salesianostriana.dam.LlinaresSomeRaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
    
}
