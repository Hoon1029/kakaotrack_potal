package kr.ac.jejunu.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponInfor {
    Integer id;
    Integer shopId;
    String name;
    Integer productId;
    String backgoundId;
    String stampId;
}
