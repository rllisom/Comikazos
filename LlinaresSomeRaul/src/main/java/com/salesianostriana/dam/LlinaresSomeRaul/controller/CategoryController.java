package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;

@Controller
@RequestMapping("/ck")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;


}
