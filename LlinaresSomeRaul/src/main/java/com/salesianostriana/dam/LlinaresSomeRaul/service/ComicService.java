package com.salesianostriana.dam.LlinaresSomeRaul.service;


import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.service.base.BaseServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.ComicRepository;


@RequiredArgsConstructor
@Service
public class ComicService extends BaseServiceImpl<Comic,Long,ComicRepository> {


	private final CategoryService categoryService;
	private final ComicRepository comicRepository;
	
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

	//EDIT COMIC
	public boolean editComic(Long id, ComicDTO dto){
		Comic original = findById(id);
		Comic editComic = ComicDTO.buildComic(dto,categoryService.findById(dto.getCategory_id()));
		editComic.setId(id);
		boolean exist = findAll().stream()
				.anyMatch(c-> c.getName().equalsIgnoreCase(editComic.getName()) &&
						c.getSyn().equalsIgnoreCase(editComic.getSyn()) &&
						c.getPrice()==editComic.getPrice() &&
						c.getSales()==editComic.getSales() &&
						c.getPages()==editComic.getPages() &&
						c.getReview()==editComic.getReview() &&
						c.getDat().equals(editComic.getDat()) &&
						c.getUrl().equalsIgnoreCase(editComic.getUrl()) &&
						c.getCategory().equals(editComic.getCategory())
				);
		boolean empty = editComic.getName() == null || editComic.getName().isBlank() ||
				editComic.getSyn() == null || editComic.getSyn().isBlank() ||
				editComic.getPrice() == null ||
				editComic.getSales() == null ||
				editComic.getPages() == null ||
				editComic.getReview() == null ||
				editComic.getDat() == null ||
				editComic.getUrl() == null || editComic.getUrl().isBlank() ||
				editComic.getCategory() == null;

		if (original.equals(editComic)){
			return false;
		}
		if(exist){
			return false;
		}
	if(empty){
		return false;
	}

		edit(editComic);
		return true;
	}

	//SEARCH
	public List<Comic> searchComics( String query){
		return comicRepository.findByNameContainingIgnoreCase(query);
	}

	//SORT
	public List<Comic> sortComics (String sort){
		List<Comic> sortComics;

		if(sort ==null){
			return findAll();
		}

		switch (sort){
			case "precioAsc" -> { sortComics = repositorio.searchByPriceAsc(); }

			case "precioDesc" ->{ sortComics = repositorio.searchByPriceDesc(); }

			case "ordenAlf" -> { sortComics = repositorio.searchByNameAsc();}

			default -> { sortComics = findAll(); }
		}
		return sortComics;
	}




}
