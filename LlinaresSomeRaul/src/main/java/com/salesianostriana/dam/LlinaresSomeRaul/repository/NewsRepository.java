package com.salesianostriana.dam.LlinaresSomeRaul.repository;

import com.salesianostriana.dam.LlinaresSomeRaul.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
}
