package com.salesianostriana.dam.LlinaresSomeRaul.controller;


import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;

@Controller
@RequestMapping("/ck")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //GET BY ID
    @GetMapping("/show/{id}")
    public String getCategory(@PathVariable Long id, Model model){
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("comicForm", new ComicDTO());
        return "getCategory";
    }

    //ADD NEW CATEGORY
    @PostMapping("/addCat")
    public String addCat(@ModelAttribute ("catForm") CategoryDTO dto, Model model){
        Category add = categoryService.addCategory(dto);

        if(add==null){
            model.addAttribute("error", "Ya existe una categoría con ese nombre");
            return "alert";
        }
        System.out.println(add);
        return "redirect:/ck";
    }
}
