package com.salesianostriana.dam.LlinaresSomeRaul.service;

import com.salesianostriana.dam.LlinaresSomeRaul.model.CartItem;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data @AllArgsConstructor @NoArgsConstructor
public class CartItemService {

    private List<CartItem> cart;
    private Long count;


    //ADD TO CART
    public void addToCart(Comic c){

        if(c!=null){
            cart.add(new CartItem(++count,c));
        }
    }

    //DELETE
    public boolean deleteFromCart(Long id){
        boolean done = false;
        Optional<CartItem> item = cart.stream().filter(i -> i.getId().equals(id)).findFirst();

        if(item.isPresent()){
            cart.remove(item.get());
            if(cart.isEmpty()){
                setCount(0L);
            }
            done = true;
        }
        return done;
    }

    public double calculateTotal(){
        return cart.stream()
                .mapToDouble(item -> item.getC().getPrice())
                .sum();
    }

}
