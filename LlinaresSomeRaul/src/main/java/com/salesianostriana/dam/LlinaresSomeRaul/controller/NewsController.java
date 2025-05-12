package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import com.salesianostriana.dam.LlinaresSomeRaul.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ck")
public class NewsController {

    private final NewsService newsService;

    //GET NEWS WEB PPAL
//    @GetMapping("")
//    public String getSomeNews(Model model){
//        model.addAttribute("news",newsService.getSomeNews());
//        return "principal";
//    }

    //GET ALL
    @GetMapping("/news")
    public String getAll(Model model){
        model.addAttribute("newsList", newsService.getAll());
        return "news";
    }

    //DELETE
    @PostMapping("/new/delete/{id}")
    public String deleteNew(@PathVariable Long id){
        newsService.deleteNew(id);
        return "redirect:/ck/news";
    }

}
