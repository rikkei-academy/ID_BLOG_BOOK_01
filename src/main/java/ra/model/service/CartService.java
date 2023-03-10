package ra.model.service;
import ra.dto.request.CartConfirm;
import ra.model.entity.Carts;
public interface CartService  extends RootService<Carts,Integer>{
    Carts mapCartConfirmToCart(Carts carts, CartConfirm confirm);
}
