package hello.small.controller;

import hello.small.domain.Product;
import hello.small.domain.ProductDto;
import hello.small.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    @GetMapping("/product/add")
    public String productPage(){
        return "product";
    }

    @ResponseBody
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(product.getPrice());
        product.setId(5L);
        productRepository.save(product);
        return "ok";

    }
}