package com.salesianostriana.dam.LlinaresSomeRaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{

    List<Comic> findByNameContainingIgnoreCase(String name);


}
