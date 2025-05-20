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
    private Long category2x1Id;
    private Long category10PerId;

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
        double globalDiscount = 0.95;
        double num = 100;
        double finalPrice;
        double total = cart.stream()
                .mapToDouble(item -> item.getC().getDiscount())
                .sum();

        finalPrice = total - discountPrice2x1() - discountPrice10Per();
        if (finalPrice > num){
            return finalPrice*globalDiscount;
        }else{
            return finalPrice;
        }

    }

    //Método que cálcula el 2x1 en cómics de DC. De dos cómics de DC, cogemos el más barato que es el que nos saldrá gratis
    public double discountPrice2x1(){
        double resul = 0.0;
        int totalItems,freeItems;
        List<CartItem> comics;

        if(category2x1Id==0L){
            return resul;
        }else{
            comics =
                    cart.stream()
                            .filter(c->c.getC().getCategory().getId().equals(category2x1Id))
                            .sorted(Comparator.comparingDouble((CartItem c)->c.getC().getDiscount()))
                            .toList();

            totalItems = comics.size();
            freeItems = totalItems/2;

            resul = comics.stream()
                    .limit(freeItems)
                    .mapToDouble(c->c.getC().getDiscount())
                    .sum();

            return resul;
        }

    }

    /*Método que acumula los descuentos de los cómics de Marvel si en el carrito hay más de 3*/
    public double discountPrice10Per(){
        double discount = 0.1;
        double resul = 0.0;
        List<CartItem> comics;

        if (category10PerId==0L){
            return resul;
        }else{
            comics= cart.stream()
                    .filter(c->c.getC().getCategory().getId().equals(category10PerId))
                    .toList();

            if (comics.size() > 3){
                for (CartItem item : comics){
                    resul += item.getC().getDiscount()*discount;
                }
            }
            return  resul;
        }
    }
}
