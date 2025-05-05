package com.salesianostriana.dam.LlinaresSomeRaul.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Comic> listComic;
}
