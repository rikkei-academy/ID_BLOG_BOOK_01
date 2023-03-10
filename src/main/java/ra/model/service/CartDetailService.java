package ra.model.service;

import org.springframework.data.domain.Page;
import ra.model.entity.CartDetail;

public interface CartDetailService extends RootService<CartDetail,Integer> {
    CartDetail findByBook_BookIdAndCarts_CartId(Integer bookId,Integer cartId);
    boolean deleteByCartDetailId(Integer id);
}
