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
        return cart.stream()
                .mapToDouble(item -> item.getC().getDiscount())
                .sum() - discountPriceDC();
    }

    public double discountPriceDC(){
        double resul = 0.0;
        List<CartItem> comicsDC =
                cart.stream()
                .filter(c->c.getC().getName().equalsIgnoreCase("dc"))
                        .sorted(Comparator.comparingDouble((CartItem c)->c.getC().getPrice()))
                        .toList();
        if(comicsDC.size()>1){

            for (int i = 0; i + 1 < comicsDC.size(); i += 2) {
                resul += comicsDC.get(i).getC().getPrice(); // El más barato es gratis
            }
        }

        return resul;
    }


}
