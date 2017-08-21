package com.yong.web;

import com.yong.domain.Book;
import com.yong.domain.Product;
import com.yong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model){
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);

        return "products/productList";
    }

    @GetMapping("/products/new")
    public String createForm(){
        return "products/createProductForm";
    }

    @PostMapping("/products/new")
    public String create(Book book){
        productService.saveProduct(book);
        return "redirect:/products";
    }
}
