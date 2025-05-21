package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.service.CartItemService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.ComicService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.NewsService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/ck")
public class ComicController {

	@Autowired
	private ComicService comicService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private NewsService newsService;

	//GET WEB PPAL
	@GetMapping(" ")
	public String getWeb(HttpSession session,Model model){
		CartItemService cartService = (CartItemService) session.getAttribute("cartService");
		if (cartService == null) {
			cartService = new CartItemService(new ArrayList<>(), 0L, 0L, 0L);
			session.setAttribute("cartService", cartService);
		}
		model.addAttribute("catForm",new CategoryDTO());
		model.addAttribute("comicForm", new ComicDTO());
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comics", comicService.getBest());
		model.addAttribute("comicList",comicService.getCatalogue());
		model.addAttribute("newsList",newsService.getSomeNews());
		model.addAttribute("category2x1",categoryService.findById(cartService.getCategory2x1Id()));
		model.addAttribute("category10Per",categoryService.findById(cartService.getCategory10PerId()));
		model.addAttribute("cart", cartService.getCart());


		return "principal";
	}

	//ABOUT US
	@GetMapping("/aboutUs")
	public String goToAboutUs(HttpSession session, Model model){
		
		CartItemService cartService = (CartItemService) session.getAttribute("cartService");
		if (cartService == null) {
			cartService = new CartItemService(new ArrayList<>(), 0L, 0L, 0L);
			session.setAttribute("cartService", cartService);
		}
		
		model.addAttribute("cart", cartService.getCart());
		
		return "aboutUs";
	}

	//GET ALL
	@GetMapping("/comics")
	public String getAll(@RequestParam(required = false) String sort, Model model, HttpSession session){
		
		CartItemService cartService = (CartItemService) session.getAttribute("cartService");
		if (cartService == null) {
			cartService = new CartItemService(new ArrayList<>(), 0L, 0L, 0L);
			session.setAttribute("cartService", cartService);
		}
		
		model.addAttribute("comicList",comicService.sortComics(sort));
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("comicForm", new ComicDTO());
		model.addAttribute("sort",sort);
		model.addAttribute("cart", cartService.getCart());
		return "allComics";
	}

	//GET BY ID
	@GetMapping("/comic/{id}")
	public String getComic(@PathVariable Long id, Model model,@RequestParam(required = false) String sort, HttpSession session ){
		
		CartItemService cartService = (CartItemService) session.getAttribute("cartService");
		if (cartService == null) {
			cartService = new CartItemService(new ArrayList<>(), 0L, 0L, 0L);
			session.setAttribute("cartService", cartService);
		}
		
		model.addAttribute("comic", comicService.getByid(id));
		model.addAttribute("comicDTO", ComicDTO.buildComicDTO(comicService.getByid(id)));
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("cart", cartService.getCart());
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
	public String searchComics(@RequestParam("query") String query, Model model,  HttpSession session) {
		
		CartItemService cartService = (CartItemService) session.getAttribute("cartService");
		if (cartService == null) {
			cartService = new CartItemService(new ArrayList<>(), 0L, 0L, 0L);
			session.setAttribute("cartService", cartService);
		}
		
		model.addAttribute("comics", comicService.searchComics(query));
		model.addAttribute("cart", cartService.getCart());
		return "search";
	}

	//SORT
	@GetMapping("/sort")
	public String sortComics(@RequestParam String sort, RedirectAttributes redirectAttributes){

		redirectAttributes.addAttribute("sort",sort);
		return "redirect:/ck/comics";
	}
}
