package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import com.salesianostriana.dam.LlinaresSomeRaul.model.News;
import com.salesianostriana.dam.LlinaresSomeRaul.service.CartItemService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.NewsService;

import jakarta.servlet.http.HttpSession;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/ck")
@Controller
public class NewsController {

	@Autowired
    private  NewsService newsService;

    //GET NEWS WEB PPAL
//    @GetMapping("")
//    public String getSomeNews(Model model){
//        model.addAttribute("news",newsService.getSomeNews());
//        return "principal";
//    }

    //GET ALL
    @GetMapping("/news")
    public String getAll(Model model,  HttpSession session){
    	
    	CartItemService cartService = (CartItemService) session.getAttribute("cartService");
		if (cartService == null) {
			cartService = new CartItemService(new ArrayList<>(), 0L, 0L, 0L);
			session.setAttribute("cartService", cartService);
		}
    	
        model.addAttribute("newsList", newsService.getAll());
        model.addAttribute("newsForm", new News());
        model.addAttribute("cart", cartService.getCart());
        return "news";
    }

    //DELETE
    @PostMapping("/new/delete/{id}")
    public String deleteNew(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(newsService.deleteNew(id)){
            redirectAttributes.addFlashAttribute("done", true);
        }else{
            redirectAttributes.addFlashAttribute("done", false);
        }
        return "redirect:/ck/news";
    }

    //EDIT
    @PostMapping("/news/edit/{id}")
    public String editNews (@PathVariable Long id, News news, RedirectAttributes redirectAttributes){
        if(newsService.editNews(id,news)){
            redirectAttributes.addFlashAttribute("success", true);
        }else{
            redirectAttributes.addFlashAttribute("success", false);
        }
        return "redirect:/ck/news";
    }

    //ADD
    @PostMapping("/addNews")
    public String addNews(@ModelAttribute("newsForm") News n, RedirectAttributes redirectAttributes){
        if(newsService.addNews(n)){
            redirectAttributes.addFlashAttribute("newsAdd", true);
        }else{
            redirectAttributes.addFlashAttribute("newsAdd", false);
        }
        return "redirect:/ck/news";
    }
}
