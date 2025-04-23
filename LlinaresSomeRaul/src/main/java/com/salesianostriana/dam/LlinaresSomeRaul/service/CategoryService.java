package com.salesianostriana.dam.LlinaresSomeRaul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.CategoryRepository;


@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    //GET ALL

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

}
