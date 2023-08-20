package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {

        return "basic/addForm";
    }
//    @PostMapping("/add")
    public String addItemV1(
                    @RequestParam String itemName,
                    @RequestParam int price,
                    @RequestParam Integer quantity,
                    Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        Item savedItem = itemRepository.save(item);

        model.addAttribute("item", savedItem);

        return "basic/item";
    }
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

    //@ModelAttribute의 name을 지정해주지 않으면 기본적으로 클래스타입을 기준으로 Item -> item 으로 변환된다.
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);

        return "basic/item";
    }
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) {
        Item savedItem = itemRepository.save(item);

        return "redirect:/basic/items/"+savedItem.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes attributes) {
        Item savedItem = itemRepository.save(item);
        attributes.addAttribute("itemId", savedItem.getId());
        attributes.addAttribute("status", true);
        //RedirectAttributes에 넣은 itemId의 값이 {itemId}에 치환된다.
        //나머지는 쿼리파라미터로 넘어간다.. ex) ?status=true
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        //리다이렉트시 {itemId} -> @PathVariable 사용한 itemId변수가 적용된다.
        return "redirect:/basic/items/{itemId}";
    }
}
