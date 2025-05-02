package com.salesianostriana.dam.LlinaresSomeRaul.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.service.base.BaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.CategoryRepository;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.ComicRepository;

@RequiredArgsConstructor
@Service 
public class ComicService extends BaseService<Comic,Long,ComicRepository> {


	private final CategoryService categoryService;

	
	//GET ALL
	public List<Comic> getAll(){
		return findAll();
	}
	
	//GET 5 BEST
	public List<Comic> getBest(){
		return findAll().stream()
		.sorted(Comparator.comparingDouble(Comic::getReview).reversed())
		.limit(5)
		.toList();
	}

	//GET 10 RANDOM
	public List<Comic> getCatalogue(){
		List<Comic> newRandomList = new ArrayList<>(findAll());
		Collections.shuffle(newRandomList);
		return newRandomList.stream()
			.limit(10)
			.toList();
	}
	//GET BY ID
	public Comic getByid(Long id) {

		return findById(id);
			
	}

	//POST NEW COMIC
	public Comic add(ComicDTO dto){
		Category cat = categoryService.findById(dto.getCategory_id());

		boolean exist = findAll().stream()
				.anyMatch(c -> c.getName().equalsIgnoreCase(dto.getName()));

		if (exist) {
			return null;
		} else {
			return save(ComicDTO.buildComic(dto,cat));
		}

	}

	//DELETE COMIC
	public boolean deleteComic(Long id) {
		if (findById(id) != null) {
			delete(findById(id));
			return true;
		}else {
			return false;
		}
	}

}
