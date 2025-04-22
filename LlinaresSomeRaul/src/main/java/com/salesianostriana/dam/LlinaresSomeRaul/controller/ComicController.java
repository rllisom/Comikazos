package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.LlinaresSomeRaul.service.ComicService;

@Controller
@RequestMapping("/ck")
public class ComicController {

	@Autowired
	private ComicService comicService;
	
	
	//GET ALL
	@GetMapping("")
	public String getAll(Model model) {
		model.addAttribute(comicService.getAll());
		return "index";
	}
	
	//GET BY ID
	@GetMapping("/{id}")
	public String getById(@RequestParam Long id, Model model) {
		model.addAttribute("comic", comicService.getByid(id));
		return "index";
	}
}
