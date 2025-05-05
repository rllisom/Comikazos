package com.salesianostriana.dam.LlinaresSomeRaul.controller;


import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.dto.ComicDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addCat(@ModelAttribute ("catForm") CategoryDTO dto, RedirectAttributes redirectAttributes){
        Category add = categoryService.addCategory(dto);

        if(add==null){
            redirectAttributes.addFlashAttribute("categoryAdd",false);
            return "redirect:/ck";
        }else {
            redirectAttributes.addFlashAttribute("categoryAdd",true);
            return "redirect:/ck";
        }

    }

    //DELETE
    @PostMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(categoryService.deleteCategory(id)){
            redirectAttributes.addFlashAttribute("done",true);
            return "redirect:/ck";
        }else{
            redirectAttributes.addFlashAttribute("done",false);
            return "redirect:/ck/show/"+id;
        }
    }

    //MODIFY NAME
    @PostMapping("/category/modify/{id}")
    public String modifyCategory(@PathVariable Long id, String name, RedirectAttributes redirectAttributes){
        if(categoryService.modifyCategory(id,name)){
            redirectAttributes.addFlashAttribute("success",true);
            return "redirect:/ck/show/"+id;
        }else{
            redirectAttributes.addFlashAttribute("success",false);
            return "redirect:/ck/show/"+id;
        }
    }
}
