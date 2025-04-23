package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.ComicService;

@Controller
@RequestMapping("/ck")
public class ComicController {

	@Autowired
	private ComicService comicService;
	@Autowired
	private CategoryService categoryService;
	
	//GET ALL
	/*@GetMapping("/comic")
	public String getAll(Model model) {
		model.addAttribute(comicService.getAll());
		return "index";
	}*/

	//GET WEB PPAL
	@GetMapping(" ")
	public String getWeb(Model model){
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comics", comicService.getBest());
		model.addAttribute("comicList",comicService.getCatalogue());
		return "principal";
	}
	

	//GET BY ID
	/*@GetMapping("/{id}")
	public String getById(@RequestParam Long id, Model model) {
		model.addAttribute("comic", comicService.getByid(id));
		return "index";
	}*/
}
