package hello.demo.controller;

import hello.demo.domain.Item;
import hello.demo.repository.ItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebServlet;
import java.util.*;
@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Controller
public class BasicItemController {
    @Data
    static class Price{
        private String name;
        private Long price;
    }
    private final ItemRepository itemRepository;
    @GetMapping("/json")
    @ResponseBody
    public Price responsePrice(@RequestHeader MultiValueMap<String,String> headerMap){
        Set<String> keys = headerMap.keySet();
        for (String key : keys){
            List<String> headerInfo = headerMap.get(key);
            System.out.print("key: "+key+" value: ");
            headerInfo.forEach(System.out::println);
        }
        Price price = new Price();
        price.setPrice(5000L);
        price.setName("삼성전자");
        return price;
    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }
    /*
    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,@RequestParam Integer price, @RequestParam Integer quantity, Model model){
        Item item = new Item(itemName,price,quantity);
        itemRepository.save(item);
        model.addAttribute("item",item);

        return "basic/item";
    }
    */
    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",item.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/basic/items/{itemId}";
        // model에 자동 추가

    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}";
    }
    @PostConstruct
    public void init(){

        itemRepository.save(new Item("testA",10000,10));
        itemRepository.save(new Item("testB",10000,10));
    }
}
