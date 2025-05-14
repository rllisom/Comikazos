package com.salesianostriana.dam.LlinaresSomeRaul.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CartItem {

    private Long id;
    private Comic c;
}
