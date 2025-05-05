package com.salesianostriana.dam.LlinaresSomeRaul.service;

import java.util.List;
import java.util.Optional;

import com.salesianostriana.dam.LlinaresSomeRaul.dto.CategoryDTO;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.service.base.BaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import com.salesianostriana.dam.LlinaresSomeRaul.repository.CategoryRepository;

@Service
public class CategoryService extends BaseService<Category,Long,CategoryRepository> {

    @Autowired
    @Lazy
    private ComicService comicService;
    //GET ALL

    public List<Category> getAll(){
        return findAll();
    }

    //GET BY ID
    public Category getById(Long id){
        return findById(id);
    }

    //ADD NEW CATEGORY
    public Category addCategory(CategoryDTO dto) {

        boolean exist = findAll().stream()
                .anyMatch(c -> c.getName().trim().equalsIgnoreCase(dto.getName().trim()));

        if (exist) {
            return null;
        } else {
            return save(CategoryDTO.buildCategory(dto));
        }
    }

    //DELETE
    public boolean deleteCategory(Long id){
        boolean done = false;
        List<Comic> listComic = comicService.findAll();
        Category undef = findById(0L);
            if(findById(id)!=null){
                done=true;
                listComic.stream()
                        .filter(c -> c.getCategory().getId().equals(id))
                        .forEach(c->c.setCategory(undef));
                deleteById(id);
            }
            return done;
    }

    //MODIFY
    public boolean modifyCategory(Long id,String name){
        Category modCat = findById(id);
        boolean exist = findAll().stream()
                .anyMatch(c -> c.getName().trim().equalsIgnoreCase(name.trim()));
        if(modCat!=null && !exist){
            modCat.setName(name);
            edit(modCat);
            return true;
        }else{
            return false;
        }
    }
}
