package kr.ac.jejunu.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupon {
    Integer couponInforId;
    String userId;
    Integer num;
}
