package com.salesianostriana.dam.LlinaresSomeRaul.dto;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {

    private String name;

    public static Category buildCategory(CategoryDTO dto){
        return Category.builder()
                .name(dto.getName())
                .build();
    }


}

