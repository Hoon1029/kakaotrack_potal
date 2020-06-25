package kr.ac.jejunu.database.object;

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
    Integer maxStampNum;
    Integer productId;
    String backgoundImgId;
    String stampImgId;
}
