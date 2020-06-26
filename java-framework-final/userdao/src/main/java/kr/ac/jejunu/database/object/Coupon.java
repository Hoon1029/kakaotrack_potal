package kr.ac.jejunu.database.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupon{
    private Integer couponInforId;
    private String customerId;
    private Integer num;
}
