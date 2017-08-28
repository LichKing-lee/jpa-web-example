package com.yong.web;

import com.yong.domain.Member;
import com.yong.domain.Product;
import com.yong.service.MemberService;
import com.yong.service.ProductService;
import com.yong.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductService productService;

    @GetMapping("/purchase")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Product> products = productService.findProducts();

        model.addAttribute("members", members);
        model.addAttribute("items", products);

        return "purchase/orderForm";
    }

    @PostMapping("/purchase")
    public String purchase(@RequestParam Long memberId,
                           @RequestParam("itemId") Long productId,
                           @RequestParam int count){
        purchaseService.order(memberId, productId, count);
        return "redirect:/purchases";
    }
}
