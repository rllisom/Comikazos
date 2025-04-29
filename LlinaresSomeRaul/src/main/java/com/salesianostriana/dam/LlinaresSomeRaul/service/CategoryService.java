package com.salesianostriana.dam.LlinaresSomeRaul.service;

import java.util.List;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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

    //GET BY ID
    public Category getById(Long id){
        return categoryRepository.findById(id).orElseThrow(
                () ->  new EntityNotFoundException("No se ha encontrado la categoría"));
    }

    //ADD NEW CATEGORY
    public Category addCategory(CategoryDTO dto) {

        boolean exist = categoryRepository.findAll().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(dto.getName()));
        System.out.println(dto);
        if (exist) {
            return null;
        } else {
            return categoryRepository.save(CategoryDTO.buildCategory(dto));
        }
    }
}
