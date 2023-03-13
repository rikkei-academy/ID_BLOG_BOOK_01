package ra.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BestSellerBook {
    private LocalDate startDate;
    private LocalDate endDate;
}
