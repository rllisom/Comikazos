package com.salesianostriana.dam.LlinaresSomeRaul.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.CategoryRepository;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.ComicRepository;

@RequiredArgsConstructor
@Service 
public class ComicService {

	private final ComicRepository comicRepository;
	private final CategoryRepository categoryRepository;


	
	//GET ALL
	public List<Comic> getAll(){
		return comicRepository.findAll();
	}
	
	//GET 5 BEST
	public List<Comic> getBest(){
		return comicRepository.findAll().stream()
		.sorted(Comparator.comparingDouble(Comic::getReview).reversed())
		.limit(5)
		.toList();
	}

	//GET 10 RANDOM
	public List<Comic> getCatalogue(){
		List<Comic> newRandomList = new ArrayList<>(comicRepository.findAll());
		Collections.shuffle(newRandomList);
		return newRandomList.stream()
			.limit(10)
			.toList();
	}
	//GET BY ID
	public Comic getByid(Long id) {

		return comicRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("No se ha encontrado el cómic"));
			
	}

	//POST NEW COMIC
	public Comic add(ComicDTO c){
		Category cat = categoryRepository.findById(c.getCategory_id()).orElse(null);
		return comicRepository.save(ComicDTO.buildComic(c,cat));

	}
}
