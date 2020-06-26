package kr.ac.jejunu.database.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponData {
    private String couponName;
    private String productName;
    private Integer productPrice;
    private Integer stampNum;
    private Integer maxStampNum;
    private Integer couponInforId;
}
