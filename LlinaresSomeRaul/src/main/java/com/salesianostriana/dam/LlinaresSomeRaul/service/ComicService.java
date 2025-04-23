package com.salesianostriana.dam.LlinaresSomeRaul.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.ComicRepository;






@Service 
public class ComicService {

	@Autowired
	private ComicRepository comicRepository;

	
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
			() -> new RuntimeException("No se ha encontrado el cómic"));
		
		
	}
}
