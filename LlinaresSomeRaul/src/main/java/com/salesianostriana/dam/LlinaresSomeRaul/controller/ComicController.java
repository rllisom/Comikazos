package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;

import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.ComicService;

@Controller
@RequestMapping("/ck")
public class ComicController {

	@Autowired
	private ComicService comicService;
	@Autowired
	private CategoryService categoryService;
	
	//GET WEB PPAL
	@GetMapping(" ")
	public String getWeb(Model model){

		model.addAttribute("catForm",new CategoryDTO());
		model.addAttribute("comicForm", new ComicDTO());
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comics", comicService.getBest());
		model.addAttribute("comicList",comicService.getCatalogue());
		return "principal";
	}
	
	//GET BY ID
	@GetMapping("/comic/{id}")
	public String getComic(@PathVariable Long id, Model model){
		model.addAttribute("comic", comicService.getByid(id));
		return "getComic";
	}

	//POST NEW COMIC
	@PostMapping("/addComic")
	public String addNewComic(@ModelAttribute("comicForm") ComicDTO c){
		comicService.add(c);
		return "redirect:/ck";
	}

}
