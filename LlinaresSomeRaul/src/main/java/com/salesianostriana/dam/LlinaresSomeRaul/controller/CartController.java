package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.service.CartItemService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.ComicService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RequestMapping("/ck")
@RequiredArgsConstructor
@Controller
public class CartController {

    private final ComicService comicService;

    //ADD COMIC TO CART
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session){
        Comic c = comicService.findById(id);
        CartItemService cartService = (CartItemService) session.getAttribute("cartService");

        if(cartService == null){
            cartService = new CartItemService(new ArrayList<>(),0L);
        }

        cartService.addToCart(c);
        session.setAttribute("cartService", cartService);

        return "redirect:/ck";

    }

    //GET ALL
    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model){
        CartItemService cartService = (CartItemService) session.getAttribute("cartService");

        if (cartService == null) {
            cartService = new CartItemService(new ArrayList<>(), 0L);
            session.setAttribute("cartService", cartService);
        }

        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("cartTotal", cartService.calculateTotal());

        return "cart";
    }
}
