package ra.dto.request;

import lombok.Data;

@Data
public class CartDetailRequest {
    private int cartId;
    private int bookId;
    private int quantity;
    private float price;
}
