package com.salesianostriana.dam.LlinaresSomeRaul.service;

import com.salesianostriana.dam.LlinaresSomeRaul.model.CartItem;
import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data @AllArgsConstructor @NoArgsConstructor
@Service
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

    //TOTAL
    public double calculateTotal(){
        double total = cart.stream()
                .mapToDouble(item -> item.getC().getDiscount())
                .sum();
        return total - discountPriceDC();
    }

    public double discountPriceDC(){
        double resul = 0.0;
        int totalItems,freeItems;
        List<CartItem> comicsDC =
                cart.stream()
                .filter(c->c.getC().getCategory().getName().equalsIgnoreCase("dc"))
                        .sorted(Comparator.comparingDouble((CartItem c)->c.getC().getDiscount()))
                        .toList();

        totalItems = comicsDC.size();
        freeItems = totalItems/2;

        resul = comicsDC.stream()
                .limit(freeItems)
                .mapToDouble(c->c.getC().getDiscount())
                .sum();
        
        return resul;
    }


}
