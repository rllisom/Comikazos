package com.salesianostriana.dam.LlinaresSomeRaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{

    List<Comic> findByNameContainingIgnoreCase(String name);

    @Query("""
			select c
			from Comic c
			order by c.price ASC
			""")
    List<Comic> searchByPriceDesc();

    @Query("""
			select c
			from Comic c
			order by c.price DESC
			""")
    List<Comic> searchByPriceAsc();

    @Query("""
            select c
			from Comic c
			order by c.name ASC
            """)
    List<Comic> searchByNameAsc();

	@Query("""
            select c
			from Comic c
			order by c.releaseDate ASC
            """)
	List<Comic> searchByDateAsc();

	@Query("""
            select c
			from Comic c
			order by c.releaseDate DESC
            """)
	List<Comic> searchByDateDesc();
}
