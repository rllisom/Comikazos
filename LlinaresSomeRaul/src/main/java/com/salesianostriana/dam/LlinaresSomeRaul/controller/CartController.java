package com.salesianostriana.dam.LlinaresSomeRaul.controller;

import com.salesianostriana.dam.LlinaresSomeRaul.model.Comic;
import com.salesianostriana.dam.LlinaresSomeRaul.service.CartItemService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.CategoryService;
import com.salesianostriana.dam.LlinaresSomeRaul.service.ComicService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@RequestMapping("/ck")
@RequiredArgsConstructor
@Controller
public class CartController {


    private final ComicService comicService;
    private final CategoryService categoryService;

    //ADD COMIC TO CART
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session){
        Comic c = comicService.findById(id);
        CartItemService cartService = (CartItemService) session.getAttribute("cartService");

        if(cartService == null){
            cartService = new CartItemService(new ArrayList<>(),0L,0L,0L);
        }

        cartService.addToCart(c);
        session.setAttribute("cartService", cartService);

        return "redirect:/ck/cart";

    }

    //GET ALL
    @GetMapping("/cart")
    public String showCart(HttpSession session, Model model){
        CartItemService cartService = (CartItemService) session.getAttribute("cartService");

        if (cartService == null) {
            cartService = new CartItemService(new ArrayList<>(), 0L,0L,0L);
            session.setAttribute("cartService", cartService);
        }

        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("cartTotal", cartService.calculateTotal());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("category2x1Id", cartService.getCategory2x1Id());
        model.addAttribute("category2x1", cartService.discountPrice2x1());
        model.addAttribute("category10PerId", cartService.getCategory10PerId());
        model.addAttribute("category10Per", cartService.discountPrice10Per());
        model.addAttribute("success",cartService.isSuperior());
        return "cart";
    }

    //DELETE
    @PostMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable Long id, HttpSession session){
        CartItemService cartService = (CartItemService) session.getAttribute("cartService");

        if (cartService != null) {
            cartService.deleteFromCart(id);
        }
        return "redirect:/ck/cart";
    }

    //Apply discounts
    @PostMapping("/cart/discounts")
    public String applyDiscounts(@RequestParam("category2x1Id") Long category2x1Id, @RequestParam("category10PerId") Long category10PerId,
                                 HttpSession session, RedirectAttributes redirectAttributes){
        CartItemService cartService = (CartItemService) session.getAttribute("cartService");
        if(cartService == null){
            cartService = new CartItemService(new ArrayList<>(), 0L,0L,0L);
            session.setAttribute("cartService", cartService);
        }
        cartService.setCategory2x1Id(category2x1Id);
        cartService.setCategory10PerId(category10PerId);
        session.setAttribute("cartService",cartService);

        redirectAttributes.addFlashAttribute("message", "Promociones aplicadas correctamente.");

        return "redirect:/ck/cart";
    }
}
