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
    String couponName;
    String productName;
    Integer productPrice;
    Integer stampNum;
    Integer maxStampNum;
    Integer couponInforId;
}
