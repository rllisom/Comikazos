package com.salesianostriana.dam.LlinaresSomeRaul.service;


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
	
	//GET BY ID
	public Comic getByid(Long id) {
	
		return comicRepository.findById(id).orElseThrow(
			() -> new RuntimeException("No se ha encontrado el cómic"));
		
		
	}
}
