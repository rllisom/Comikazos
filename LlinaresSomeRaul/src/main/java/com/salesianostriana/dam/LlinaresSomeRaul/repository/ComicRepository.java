package com.salesianostriana.dam.LlinaresSomeRaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{

	
}
