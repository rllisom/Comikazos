package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.service.CartItemService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.NewsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;

import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.ComicService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ck")
public class ComicController {


	private final ComicService comicService;
	private final CategoryService categoryService;
	private final NewsService newsService;
	
	//GET WEB PPAL
	@GetMapping(" ")
	public String getWeb(Model model){

		model.addAttribute("catForm",new CategoryDTO());
		model.addAttribute("comicForm", new ComicDTO());
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comics", comicService.getBest());
		model.addAttribute("comicList",comicService.getCatalogue());
		model.addAttribute("newsList",newsService.getSomeNews());
		return "principal";
	}

	//GET ALL
	@GetMapping("/comics")
	public String getAll(Model model){
		model.addAttribute("comicList",comicService.getAll());
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comicForm", new ComicDTO());
		return "allComics";
	}

	//GET BY ID
	@GetMapping("/comic/{id}")
	public String getComic(@PathVariable Long id, Model model){
		model.addAttribute("comic", comicService.getByid(id));
		model.addAttribute("comicDTO", ComicDTO.buildComicDTO(comicService.getByid(id)));
		model.addAttribute("categories", categoryService.getAll());
		return "getComic";
	}

	//POST NEW COMIC
	@PostMapping("/addComic")
	public String addNewComic(@ModelAttribute("comicForm") ComicDTO c){
		comicService.add(c);
		return "redirect:/ck";
	}

	//DELETE COMIC
	@PostMapping("/comic/delete/{id}")
	public String deleteComic(@PathVariable Long id, RedirectAttributes redirectAttributes){
		if(comicService.deleteComic(id)){
			redirectAttributes.addFlashAttribute("doneComic",true);
			return "redirect:/ck";
		}else{
			redirectAttributes.addFlashAttribute("doneComic",false);
			return "redirect:/ck/comic/"+id;
		}
	}

	//EDIT COMIC
	@PostMapping("/comic/edit/{id}")
	public String editComic(@PathVariable Long id, ComicDTO dto, RedirectAttributes redirectAttributes){
		if(comicService.editComic(id,dto)){
			redirectAttributes.addFlashAttribute("doneEdit",true);
			return "redirect:/ck/comic/"+id;
		}else{
			redirectAttributes.addFlashAttribute("doneEdit",false);
			return "redirect:/ck/comic/"+id;
		}
	}

	//SEARCH
	@GetMapping("/comic/search")
	public String searchComics(@RequestParam("query") String query, Model model) {
		model.addAttribute("comics", comicService.searchComics(query));
		return "search";
	}

}
