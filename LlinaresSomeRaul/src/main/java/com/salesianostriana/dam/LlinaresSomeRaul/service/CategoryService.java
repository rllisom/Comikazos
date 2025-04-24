package com.salesianostriana.dam.LlinaresSomeRaul.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {
    

    private final CategoryRepository categoryRepository;

    //GET ALL

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

}
