package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
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
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comics", comicService.getBest());
		model.addAttribute("comicList",comicService.getCatalogue());
		return "principal";
	}
	
	//GET NEW COMIC
	@GetMapping("/new")
	public String getNewComic(Model model){
		ComicDTO c = new ComicDTO();
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comicForm", c);
		return "addComic";
	}

	//POST NEW COMIC
	@PostMapping("/add")
	public String addNewComic(@ModelAttribute("comicForm") ComicDTO c){
		comicService.add(c);
		return "redirect:/ck";
	}

}
