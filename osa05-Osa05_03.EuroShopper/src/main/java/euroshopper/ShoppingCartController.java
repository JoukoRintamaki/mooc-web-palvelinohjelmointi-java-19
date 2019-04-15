package euroshopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {
    @Autowired
    ShoppingCart shoppingCart;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/cart")
    public String getShoppingCart(Model model) {
        model.addAttribute("items", shoppingCart.getItems());
        model.addAttribute("sum", shoppingCart
                .getItems()
                .values()
                .stream()
                .mapToLong(
                        Long::longValue).sum());
        return "cart";
    }

    @PostMapping("/cart/items/{id}")
    public String postItemToShoppintCart(@PathVariable Long id) {
        shoppingCart.addToCart(itemRepository.getOne(id));
        return "redirect:/cart";
    }
}