package euroshopper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private Map<Item, Long> cart = new HashMap<>();

    public Map<Item, Long> getItems() {
        return cart;
    }

    public void addToCart(Item item) {
        cart.merge(item, 1L, Long::sum);
    }
}