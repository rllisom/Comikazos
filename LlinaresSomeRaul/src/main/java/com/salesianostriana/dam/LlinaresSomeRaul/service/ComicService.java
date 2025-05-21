package com.salesianostriana.dam.LlinaresSomeRaul.service;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.ComicRepository;
import com.salesianostriana.dam.LlinaresSomeRaul.service.base.BaseServiceImpl;



@Service
public class ComicService extends BaseServiceImpl<Comic,Long,ComicRepository> {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ComicRepository comicRepository;
	
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

		return findById(id).get();
			
	}

	//POST NEW COMIC
	public Comic add(ComicDTO dto){
		Category cat = categoryService.findById(dto.getCategory_id()).get();
		Comic newComic = ComicDTO.buildComic(dto,cat);
		boolean exist = findAll().stream()
				.anyMatch(c -> c.getName().trim().equalsIgnoreCase(newComic.getName().trim()) ||					
						c.getUrl().trim().equalsIgnoreCase(newComic.getUrl().trim()));

		if (exist) {
			return null;
		} else {
			return save(newComic);
		}

	}

	//DELETE COMIC
	public boolean deleteComic(Long id) {
		if (findById(id) != null) {
			delete(findById(id).get());
			return true;
		}else {
			return false;
		}
	}

	//EDIT COMIC
	public boolean editComic(Long id, ComicDTO dto){
		Comic original = findById(id).get();
		Comic editComic = ComicDTO.buildComic(dto,categoryService.findById(dto.getCategory_id()).get());
		editComic.setId(id);
		boolean exist = findAll().stream()
				.anyMatch(c-> c.getName().equalsIgnoreCase(editComic.getName()) &&
						c.getSyn().equalsIgnoreCase(editComic.getSyn()) &&
						c.getPrice().equals(editComic.getPrice()) &&
						c.getSales().equals(editComic.getSales()) &&
						c.getPages().equals(editComic.getPages()) &&
						c.getReview().equals(editComic.getReview()) &&
						c.getReleaseDate().equals(editComic.getReleaseDate()) &&
						c.getUrl().equalsIgnoreCase(editComic.getUrl()) &&
						c.getCategory().equals(editComic.getCategory())
				);
		boolean empty = editComic.getName() == null || editComic.getName().isBlank() ||
				editComic.getSyn() == null || editComic.getSyn().isBlank() ||
				editComic.getPrice() == null ||
				editComic.getSales() == null ||
				editComic.getPages() == null ||
				editComic.getReview() == null ||
				editComic.getReleaseDate() == null ||
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

			case "dateAsc" -> {sortComics = repositorio.searchByDateAsc();}

			case  "dateDesc" -> {sortComics = repositorio.searchByDateDesc();}

			default -> { sortComics = findAll(); }
		}
		return sortComics;
	}




}
